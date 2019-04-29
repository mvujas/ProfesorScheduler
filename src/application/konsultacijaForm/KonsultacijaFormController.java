package application.konsultacijaForm;

import java.text.ParseException;
import java.util.Objects;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import application.ComponentController;
import application.OnShowEvent;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import models.InvalidStudentInformationException;
import models.Obaveza;
import models.Smer;
import models.Student;
import utils.DateUtils;

public class KonsultacijaFormController extends ComponentController {
	@FXML
	private Label headingLabel;
	@FXML
	private TextField imeField;
	@FXML
	private TextField prezimeField;
	@FXML
	private TextField brIndeksaField;
	@FXML
	private ComboBox<Smer> smerField;
	@FXML
	private TextField vremePocetkaField;
	@FXML
	private Label errorLabel;
	@FXML
	private Button saveBtn;
	
	private void resetForm() {
		imeField.setText("");
		prezimeField.setText("");
		brIndeksaField.setText("");
		vremePocetkaField.setText("");
		smerField.setValue(null);
		errorLabel.setText("");
	}
	
	private void changeHeadingLabelDate(LocalDate date) {
		headingLabel.setText(String.format("Zakazivanje konsultacija za %s", DateUtils.getDatumAsString(date)));
	}
	
	private void setSmerovi() {
		smerField.setItems(FXCollections.observableArrayList(data.getSmerovi().values()));
	}	
	
	@Override
	public void onModelSet() {
		data.getPrikazaniDatum().addListener((observable, oldValue, newValue) -> {
			changeHeadingLabelDate(newValue);
		});
		changeHeadingLabelDate(data.getPrikazaniDatum().get() );
		data.getSmerovi().addListener((MapChangeListener<Integer,Smer>)change -> this.setSmerovi());
		smerField.setConverter(new StringConverter<Smer>() {
			
			@Override
			public String toString(Smer object) {
				return object.getNaziv();
			}
			
			@Override
			public Smer fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		setSmerovi();
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		resetForm();
	}
	
	@FXML
	private void initialize() {
		saveBtn.setOnAction(e -> handleSave());
	}
	
	private static class FormData {
		String ime;
		String prezime;
		String brojIndeksa;
		Smer smer;
		LocalTime vremePocetka;
		LocalTime vremeKraja;
	}
	
	private FormData parseForm() throws ParseException {
		FormData formData = new FormData();
		formData.ime = imeField.getText().trim();
		formData.prezime = prezimeField.getText().trim();
		formData.brojIndeksa = brIndeksaField.getText().replaceAll(" ", "");
		formData.smer = smerField.getValue();
		String time = vremePocetkaField.getText().replaceAll(" ", "");
		formData.vremePocetka = DateUtils.strToLocalTime(time);
		formData.vremeKraja = new LocalTime(formData.vremePocetka).plusMinutes(30).minusSeconds(1);
		if(formData.vremeKraja.isBefore(formData.vremePocetka)) {
			throw new ParseException("Vreme je suvise kasno", 0);
		}
		return formData;
	}
	
	private void handleSave() {
		try {
			FormData formData = parseForm();
			Student student = new Student();
			student.setIme(formData.ime);
			student.setPrezime(formData.prezime);
			student.setBrojIndeksa(formData.brojIndeksa);
			student.setSmer(formData.smer);
			
			Student studentPodDatimIdom = data.getStudenti().get(student.getBrojIndeksa());
			boolean doesStudentExist = studentPodDatimIdom != null;
			if(doesStudentExist && !Objects.equals(studentPodDatimIdom, student)) {
				errorLabel.setText(
						"* Student pod datim brojem indeksom vec \n"
						+ "  postoji u sistemu, ali sa razlicitim podacima");
				return;
			}
			
			Obaveza obaveza = new Obaveza();
			obaveza.setNaziv("Konsultacije");
			LocalDate datum = data.getPrikazaniDatum().get();
			obaveza.setVremePocetka(datum.toLocalDateTime(formData.vremePocetka));
			obaveza.setVremeKraja(datum.toLocalDateTime(formData.vremeKraja));
			obaveza.setStudent(doesStudentExist ? studentPodDatimIdom : student);
			ObservableList<Obaveza> obavezeNaDatiDan = data.getObaveze().get(datum);
			if(obavezeNaDatiDan != null) {
				for(Obaveza o: obavezeNaDatiDan) {
					if(Obaveza.doOverlap(o, obaveza)) {
						errorLabel.setText(
								"* Vec postoji obaveza koja se odrzava delom u isto vreme");
						return;
					}
				}
			}
			if(!doesStudentExist) {
				if(!data.addStudent(obaveza.getStudent())) {
					errorLabel.setText(
							"* Doslo je do greske prilikom unosa studenta");
					return;
				}
			}
			if(!data.addObaveza(obaveza)) {

				errorLabel.setText(
						"* Doslo je do greske prilikom unosa obaveze");
			}
			screenController.activate("obavezeList");
			resetForm();
		}
		catch(InvalidStudentInformationException e) {
			errorLabel.setText("* " + e.getRazlog());
		}
		catch(ParseException e) {
			errorLabel.setText("* " + e.getMessage());
		}
	}
}

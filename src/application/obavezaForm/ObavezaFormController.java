package application.obavezaForm;

import java.text.ParseException;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import application.ComponentController;
import application.OnShowEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Obaveza;
import utils.DateUtils;

public class ObavezaFormController extends ComponentController {
	@FXML
	private Label headingLabel;
	@FXML
	private TextField nazivField;
	@FXML
	private TextField vremePocetkaField;
	@FXML
	private TextField vremeKrajaField;
	@FXML
	private Label errorLabel;
	@FXML
	private Button saveBtn;
	
	private void resetForm() {
		nazivField.setText("");
		vremePocetkaField.setText("");
		vremeKrajaField.setText("");
		errorLabel.setText("");
	}
	
	private void changeHeadingLabelDate(LocalDate date) {
		headingLabel.setText(String.format("Nova obaveza za %s", DateUtils.getDatumAsString(date)));
	}
	
	@Override
	public void onModelSet() {
		data.getPrikazaniDatum().addListener((observable, oldValue, newValue) -> {
			changeHeadingLabelDate(newValue);
		});
		changeHeadingLabelDate(data.getPrikazaniDatum().get() );
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
		String naziv;
		LocalTime vremePocetka;
		LocalTime vremeKraja;
	}
	
	private FormData parseForm() throws ParseException {
		FormData formData = new FormData();
		formData.naziv = nazivField.getText().trim();
		if(formData.naziv.length() == 0) {
			throw new ParseException("Naziv obaveze se mora uneti", 0);
		}
		String vremePocetka = vremePocetkaField.getText().replaceAll(" ", "");
		String vremeKraja = vremeKrajaField.getText().replaceAll(" ", "");
		formData.vremePocetka = DateUtils.strToLocalTime(vremePocetka);
		formData.vremeKraja = DateUtils.strToLocalTime(vremeKraja).minusSeconds(1);
		if(formData.vremeKraja.isBefore(formData.vremePocetka)) {
			throw new ParseException("Vreme kraja obaveze mora biti pre vremena pocetka", 0);
		}
		return formData;
	}
	
	private void handleSave() {
		try {
			FormData formData = parseForm();
			
			Obaveza obaveza = new Obaveza();
			obaveza.setNaziv(formData.naziv);
			LocalDate datum = data.getPrikazaniDatum().get();
			obaveza.setVremePocetka(datum.toLocalDateTime(formData.vremePocetka));
			obaveza.setVremeKraja(datum.toLocalDateTime(formData.vremeKraja));
			obaveza.setStudent(null);
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
			if(!data.addObaveza(obaveza)) {
				errorLabel.setText(
						"* Doslo je do greske prilikom unosa obaveze");
			}
			screenController.activate("obavezeList");
			resetForm();
		}
		catch(ParseException e) {
			errorLabel.setText("* " + e.getMessage());
		}
	}
}

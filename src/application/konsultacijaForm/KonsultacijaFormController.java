package application.konsultacijaForm;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import application.ComponentController;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import models.Smer;
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
	
	private static class FormData {
		String ime;
		String prezime;
		String brojIndeksa;
		Smer smer;
		LocalTime vremePocetka;
	}
	
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
	private FormData parseForm() {
		FormData formData = new FormData();
		formData.ime = imeField.getText().trim();
		formData.prezime = prezimeField.getText().trim();
		formData.brojIndeksa = brIndeksaField.getText().replaceAll(" ", "");
		formData.smer = smerField.getValue();
		formData.vremePocetka = TIME_FORMATTER.parseLocalTime(vremePocetkaField.getText().replaceAll(" ", ""));
		return formData;
	}
	
	private void handleSave() {
		
	}
}

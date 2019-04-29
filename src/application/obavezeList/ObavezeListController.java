package application.obavezeList;

import org.joda.time.LocalDate;

import application.ComponentController;
import application.OnShowEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Obaveza;
import models.Student;
import models.Uloga;
import utils.DateUtils;

public class ObavezeListController extends ComponentController {
	@FXML
	private Button obavezaAddButton;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Label labelIznadListe;
	
	@FXML
	private TableColumn<Obaveza, String> opisObavezeKolona;
	@FXML
	private TableColumn<Obaveza, String> vremePocetkaObavezeKolona;
	@FXML
	private TableColumn<Obaveza, String> vremeKrajaObavezeKolona;
	@FXML
	private TableColumn<Obaveza, String> studentKolona;
	@FXML
	private TableView<Obaveza> tabela;
	
	private void setAppropriateButton() {
		String buttonText = "";
		final String nextPage;
		if(data.getUloga() == Uloga.PROFESOR) {
			buttonText = "Nova obaveza";
			nextPage = "obavezaForm";
		}
		else if(data.getUloga() == Uloga.STUDENT) {
			buttonText = "Zakazi konsultacije";
			nextPage = "konsultacijaForm";
		}
		else {
			nextPage = null;
		}
		obavezaAddButton.setText(buttonText);
		obavezaAddButton.setOnAction(e -> screenController.activate(nextPage));
	}
	
	private void setListDate(LocalDate date) {
		labelIznadListe.setText(String.format(
				"Obaveze profesora za dan %s su:", DateUtils.getDatumAsString(date)));
		ObservableList<Obaveza> obaveze = data.getObaveze().get(date);
		tabela.setItems(obaveze);
	}
	
	@Override
	public void onModelSet() {
		LocalDate datum = data.getPrikazaniDatum().get();
		datePicker.setValue(DateUtils.jodaLocalDatetoJavaLocalDate(datum));
		setListDate(datum);
		data.getPrikazaniDatum().addListener((observable, oldValue, newValue) -> {
			setListDate(newValue);
		});
	}
	
	@Override
	public void onShow(OnShowEvent event) {
		setAppropriateButton();
		setListDate(data.getPrikazaniDatum().get());
	}
	
	@FXML
	private void initialize() {
		datePicker.setOnAction(e -> {
			data.getPrikazaniDatum().set(DateUtils.javaLocalDateToJodaLocalDate(datePicker.getValue()));
		});
		
		// Property bi bili nepotrebni da je od pocetka dobro radjen projekat
		opisObavezeKolona.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getNaziv()));
		vremePocetkaObavezeKolona.setCellValueFactory(e -> new SimpleStringProperty(DateUtils.getTimeAsString(e.getValue().getVremePocetka())));
		vremeKrajaObavezeKolona.setCellValueFactory(e -> new SimpleStringProperty(DateUtils.getTimeAsString(e.getValue().getVremeKraja())));
		studentKolona.setCellValueFactory(e -> {
			Student s = e.getValue().getStudent();
			return new SimpleStringProperty(s == null ? "" : String.format("%s %s", s.getIme(), s.getPrezime()));
		});
	}
}

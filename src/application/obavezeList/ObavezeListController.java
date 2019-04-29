package application.obavezeList;

import application.ComponentController;
import application.OnShowEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import models.Obaveza;
import models.Uloga;

public class ObavezeListController extends ComponentController {
	@FXML
	private Button obavezaAddButton;
	
	@FXML
	private TableColumn<Obaveza, String> opisObavezeKolona;
	@FXML
	private TableColumn<Obaveza, String> vremePocetkaObavezeKolona;
	@FXML
	private TableColumn<Obaveza, String> vremeKrajaObavezeKolona;
	@FXML
	private TableColumn<Obaveza, String> studentKolona;
	
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
	
	@Override
	public void onShow(OnShowEvent event) {
		setAppropriateButton();
	}
	
	@FXML
	private void initialize() {
	}
}

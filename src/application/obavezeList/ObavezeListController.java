package application.obavezeList;

import application.ComponentController;
import application.OnShowEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.Uloga;

public class ObavezeListController extends ComponentController {
	@FXML
	private Button obavezaAddButton;
	
	@Override
	public void onShow(OnShowEvent event) {
		String buttonText = "";
		if(data.getUloga() == Uloga.PROFESOR) {
			buttonText = "Nova obaveza";
		}
		else if(data.getUloga() == Uloga.STUDENT) {
			buttonText = "Zakazi konsultacije";
		}
		obavezaAddButton.setText(buttonText);
	}
}

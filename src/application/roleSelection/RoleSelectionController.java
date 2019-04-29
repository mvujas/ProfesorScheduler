package application.roleSelection;

import application.ComponentController;
import application.OnShowEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.Uloga;

public class RoleSelectionController extends ComponentController {
    @FXML
    private Button profesorBtn;
    @FXML
    private Button studentBtn;
    
    @FXML
    private void initialize() {
    	profesorBtn.setOnAction(e -> chooseRole(Uloga.PROFESOR));
    	studentBtn.setOnAction(e -> chooseRole(Uloga.STUDENT));
    }
	
	private void chooseRole(Uloga uloga) {
		data.setUloga(uloga);
		screenController.activate("obavezeList");
	}
}

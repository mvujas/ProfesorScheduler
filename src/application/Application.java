package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
	
	private ScreenController screenController;
	
	private Parent loadFXML(String fxmlFilePath) {
		try {
			return FXMLLoader.load(
					getClass().getResource(fxmlFilePath));
		} catch (Exception e) {
			return ERROR_PAGE;
		}
	}
	
	private void loadScenes() {
		screenController.addScreen("roleSelection", 
				loadFXML("/application/roleSelection/roleSelection.fxml"));
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new BorderPane(new Label("Welcome!")));
		primaryStage.setScene(scene);
		screenController = new ScreenController(scene);
		loadScenes();
		screenController.activate("roleSelection");
		primaryStage.show();
	}
	
	
	
	private static final Parent ERROR_PAGE = 
			new BorderPane(
					new Label(
							"Doslo je do greske prilikom "
							+ "ucitavanje ove komponente"));

}

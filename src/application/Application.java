package application;

import database.SQLDataManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.DataModel;

public class Application extends javafx.application.Application {
	
	private ScreenController screenController;
	private DataModel data;
	
	{
		data = new DataModel(new SQLDataManager("db.db"));
	}
	
	private Parent loadFXML(String fxmlFilePath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
		try {
			Parent pane = loader.load();
			ComponentController controller = 
					loader.<ComponentController>getController();
			pane.addEventHandler(OnShowEvent.SHOW_EVENT, controller::onShow);
			controller.setScreenController(screenController);
			controller.setData(data);
			return pane;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_PAGE;
		}
	}
	
	private void loadScenes() {
		screenController.addScreen("roleSelection", 
				loadFXML("/application/roleSelection/roleSelection.fxml"));
		screenController.addScreen("obavezeList", 
				loadFXML("/application/obavezeList/obavezeList.fxml"));
		screenController.addScreen("obavezaForm", 
				loadFXML("/application/obavezaForm/obavezaForm.fxml"));
		screenController.addScreen("konsultacijaForm", 
				loadFXML("/application/konsultacijaForm/konsultacijaForm.fxml"));
	}
	
	private void closingApplicationProcedure(WindowEvent e) {
		data.close();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new BorderPane(new Label("Welcome!")));
		primaryStage.setTitle("Rokovnik");
		primaryStage.getIcons().add(new Image("favico100.png"));
		primaryStage.setScene(scene);
		screenController = new ScreenController(scene);
		loadScenes();
		screenController.activate("roleSelection");
		primaryStage.setOnCloseRequest(this::closingApplicationProcedure);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	
	private static final Parent ERROR_PAGE = 
			new BorderPane(
					new Label(
							"Doslo je do greske prilikom "
							+ "ucitavanje ove komponente"));

}

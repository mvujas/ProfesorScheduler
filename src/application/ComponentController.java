package application;

import javafx.event.EventHandler;
import models.DataModel;

public abstract class ComponentController {
	protected DataModel data;
	protected ScreenController screenController;
	
	public ComponentController(DataModel data) {
		this.data = data;
	}
	
	public ComponentController() {
		this(null);
	}
	
	public void setData(DataModel data) {
		this.data = data;
	}
	
	public void setScreenController(ScreenController screenController) {
		this.screenController = screenController;
	}
	
	@SuppressWarnings("unused")
	public void onShow(OnShowEvent event) {
	}
}

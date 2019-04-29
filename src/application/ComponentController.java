package application;

import models.DataModel;

public class ComponentController {
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
}

package application;

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
	
	public void onShow(OnShowEvent event) {
	}
	
	public void onModelSet() {
	}
}

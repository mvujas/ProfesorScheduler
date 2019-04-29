package application.obavezaForm;

import org.joda.time.LocalDate;

import application.ComponentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.DateUtils;

public class ObavezaFormController extends ComponentController {
	@FXML
	private Label headingLabel;
	
	private void changeHeadingLabelDate(LocalDate date) {
		headingLabel.setText(String.format("Nova obaveza za %s", DateUtils.getDatumAsString(date)));
	}
	
	@Override
	public void onModelSet() {
		data.getPrikazaniDatum().addListener((observable, oldValue, newValue) -> {
			changeHeadingLabelDate(newValue);
		});
		changeHeadingLabelDate(data.getPrikazaniDatum().get() );
	}
}

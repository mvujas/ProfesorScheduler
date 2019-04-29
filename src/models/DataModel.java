package models;

import java.util.Map;

import database.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class DataModel {
	private DataManager dataUtils;
	private ObservableMap<Integer, Smer> smerovi;
	private ObservableMap<String, Student> studenti;
	private Uloga uloga = null;

	public DataModel(DataManager dataUtils) {
		this.dataUtils = dataUtils;
		loadModelFromDatabase();
		System.out.println(smerovi);
		System.out.println(studenti);
	}
	
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
	private void loadModelFromDatabase() {
		smerovi = FXCollections.observableMap(dataUtils.getAllSmer());
		Map<String, Student> sviStudenti = dataUtils.getAllStudent();
		sviStudenti.entrySet()
			.stream()
			.forEach(studentEntry -> {
				Student student = studentEntry.getValue();
				try {
					student.setSmer(smerovi.get(student.getSmerId()));
				} catch (InvalidStudentInformationException e) {}
			});
		studenti = FXCollections.observableMap(sviStudenti);
	}
	
	public void close() {
		dataUtils.close();
	}
	
	
}

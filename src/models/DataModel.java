package models;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import database.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class DataModel {
	private DataManager dataUtils;
	private ObservableMap<Integer, Smer> smerovi;
	private ObservableMap<String, Student> studenti;
	private ObservableMap<LocalDate, ObservableList<Obaveza>> obaveze;
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
		Map<LocalDate, List<Obaveza>> sveObaveze = dataUtils.getAllObaveze();
		sveObaveze.entrySet()
			.stream()
			.forEach(lista -> lista.getValue().stream().forEach(obaveza -> {
				if(obaveza.getStudentBrojIndeksa() != null) {
					obaveza.setStudent(studenti.get(obaveza.getStudentBrojIndeksa()));
				}
			}));
		System.out.println(sveObaveze);
	}
	
	public void close() {
		dataUtils.close();
	}
	
	
}

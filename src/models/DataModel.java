package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import database.DataManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class DataModel {
	private DataManager dataUtils;
	private ObservableMap<Integer, Smer> smerovi;
	private ObservableMap<String, Student> studenti;
	private Map<LocalDate, ObservableList<Obaveza>> obaveze;
	private Uloga uloga = null;
	private SimpleObjectProperty<LocalDate> prikazaniDatum = new SimpleObjectProperty<>(new LocalDate());

	public DataModel(DataManager dataUtils) {
		this.dataUtils = dataUtils;
		loadModelFromDatabase();
	}
	
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
	public SimpleObjectProperty<LocalDate> getPrikazaniDatum() {
		return prikazaniDatum;
	}

	public void setPrikazaniDatum(SimpleObjectProperty<LocalDate> prikazaniDatum) {
		this.prikazaniDatum = prikazaniDatum;
	}

	public ObservableMap<Integer, Smer> getSmerovi() {
		return smerovi;
	}

	public void setSmerovi(ObservableMap<Integer, Smer> smerovi) {
		this.smerovi = smerovi;
	}

	public ObservableMap<String, Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(ObservableMap<String, Student> studenti) {
		this.studenti = studenti;
	}

	public Map<LocalDate, ObservableList<Obaveza>> getObaveze() {
		return obaveze;
	}

	public void setObaveze(ObservableMap<LocalDate, ObservableList<Obaveza>> obaveze) {
		this.obaveze = obaveze;
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
		obaveze = new HashMap<>();
		sveObaveze.entrySet()
			.stream()
			.forEach(lista ->  {
				lista.getValue().stream().forEach(obaveza -> {
					if(obaveza.getStudentBrojIndeksa() != null) {
						obaveza.setStudent(studenti.get(obaveza.getStudentBrojIndeksa()));
					}
				});
				obaveze.put(lista.getKey(), FXCollections.observableList(lista.getValue()));
			});
	}
	
	public void close() {
		dataUtils.close();
	}
	
	
}

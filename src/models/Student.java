package models;

import utils.StudentUtils;

public class Student {
	private String brojIndeksa;
	private String ime;
	private String prezime;
	private Smer smer;
	
	public Student() {}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa)
			throws InvalidStudentInformationException {
		if(!StudentUtils.isBrojIndeksaValid(brojIndeksa)) {
			throw new InvalidStudentInformationException(
					"Nevalidan broj indeksa");
		}
		
		this.brojIndeksa = brojIndeksa.trim();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime)
			throws InvalidStudentInformationException {
		if(!StudentUtils.isImeValid(ime)) {
			throw new InvalidStudentInformationException(
					"Nevalidno ime");
		}
		this.ime = ime.trim();
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime)
			throws InvalidStudentInformationException {
		if(!StudentUtils.isPrezimeValid(prezime)) {
			throw new InvalidStudentInformationException(
					"Nevalidno prezime");
		}
		this.prezime = prezime.trim();
	}

	public Smer getSmer() {
		return smer;
	}

	public void setSmer(Smer smer)
			throws InvalidStudentInformationException {
		if(smer == null) {
			throw new InvalidStudentInformationException(
					"Smer ne sme biti null");
		}
		this.smer = smer;
	}
}

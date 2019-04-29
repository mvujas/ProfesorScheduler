package models;

import java.util.Map;
import java.util.Objects;

import utils.StudentUtils;

public class Student {
	private String brojIndeksa;
	private String ime;
	private String prezime;
	/*
	 * Ovaj podatak je visak ali da ne bi bilo dupliranja podatak prilikom upita bice tu
	 * Programer se savetuje da ne koristi ovo u svom programu sem onoga za sta je namenjen
	 */
	private Integer smerId;
	private Smer smer;
	
	public Student() {}

	public Integer getSmerId() {
		return smerId;
	}

	public void setSmerId(Integer smerId) {
		this.smerId = smerId;
	}

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
					"Smer ne sme biti prazan");
		}
		this.smer = smer;
	}
	
	public static Student fromRowMap(Map<String, Object> mapa) {
		Student student = new Student();
		// Podaci u bazi bi trebalo da su uvek u vazecem formatu
		try {
			student.setBrojIndeksa((String)mapa.get("broj_indeksa"));
			student.setIme((String)mapa.get("ime"));
			student.setPrezime((String)mapa.get("prezime"));
		}
		catch(Exception e) {
			
		}
		student.setSmerId((Integer)mapa.get("smer_s_id"));
		return student;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", ime, prezime);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Student s = (Student)obj;
		
		return Objects.equals(ime, s.ime) &&
				Objects.equals(prezime, s.prezime) &&
				Objects.equals(brojIndeksa, s.brojIndeksa) &&
				Objects.equals(smer, s.smer);
	}
}

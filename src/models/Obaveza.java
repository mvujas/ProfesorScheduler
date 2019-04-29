package models;

import java.util.Map;

import org.joda.time.Interval;
import org.joda.time.LocalDateTime;

import utils.DateUtils;

public class Obaveza {
	private Integer id;
	private String naziv;
	private LocalDateTime vremePocetka;
	private LocalDateTime vremeKraja;
	/*
	 * Ovaj podatak je visak ali da ne bi bilo dupliranja podatak prilikom upita bice tu
	 * Programer se savetuje da ne koristi ovo u svom programu sem onoga za sta je namenjen
	 */
	private String studentBrojIndeksa;
	private Student student;
	
	public Obaveza() {
		
	}
	
	public String getStudentBrojIndeksa() {
		return studentBrojIndeksa;
	}
	public void setStudentBrojIndeksa(String studentBrojIndeksa) {
		this.studentBrojIndeksa = studentBrojIndeksa;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public LocalDateTime getVremePocetka() {
		return vremePocetka;
	}
	public void setVremePocetka(LocalDateTime vremePocetka) {
		this.vremePocetka = vremePocetka;
	}
	public void setVremePocetka(int UTC) {
		setVremePocetka(new LocalDateTime(DateUtils.utcToMillis(UTC)));
	}
	public LocalDateTime getVremeKraja() {
		return vremeKraja;
	}
	public void setVremeKraja(LocalDateTime vremeKraja) {
		this.vremeKraja = vremeKraja;
	}
	public void setVremeKraja(int UTC) {
		setVremeKraja(new LocalDateTime(DateUtils.utcToMillis(UTC)));
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public static boolean doOverlap(Obaveza o1, Obaveza o2) {
		return new Interval(o1.vremePocetka.toDateTime(), o1.vremeKraja.toDateTime()).abuts(
				new Interval(o2.vremePocetka.toDateTime(), o2.vremeKraja.toDateTime()));
	}
	
	public static Obaveza fromRowMap(Map<String, Object> mapa) {
		Obaveza obaveza = new Obaveza();
		obaveza.setId((int)mapa.get("o_id"));
		obaveza.setNaziv((String)mapa.get("naziv"));
		obaveza.setVremePocetka((int)mapa.get("vreme_pocetka"));
		obaveza.setVremeKraja((int)mapa.get("vreme_kraja"));
		obaveza.setStudentBrojIndeksa((String)mapa.get("student_broj_indeksa"));
		return obaveza;
	}
	
	@Override
	public String toString() {
		return String.format("%s [%s, %s]", naziv, DateUtils.getTimeAsString(vremePocetka), 
				DateUtils.getTimeAsString(vremeKraja));
	}
	
}

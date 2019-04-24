package models;

import java.util.Map;

/**
 * Korisnik cuva podatke samo podatke samo koji ce aplikaciji biti neophodni
 * Baza ce obavljati autentikaciju
 */
public abstract class Korisnik {
	private Integer id;
	private String ime, prezime;
	
	public Korisnik(Integer id, String ime, String prezime) {
		super();
		setId(id);
		setIme(ime);
		setPrezime(prezime);
	}
	
	public Korisnik(String ime, String prezime) {
		this(null, ime, prezime);
	}
	
	public Korisnik() {
		this(null, null);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", ime, prezime);
	}
	
	public static Korisnik fromDBRowMap(Map<String, Object> mapa) {
		try {
			String uloga = ((String)mapa.get("naziv_uloge")).toLowerCase();
			
			Korisnik rezultat;
			switch(uloga) {
				case "profesor":
					rezultat = new Profesor();
					break;
				case "student":
					rezultat = new Student();
					break;
				default:
					rezultat = null;
			}
			rezultat.setId((Integer)mapa.get("k_id"));
			rezultat.setIme((String)mapa.get("ime"));
			rezultat.setPrezime((String)mapa.get("prezime"));
			return rezultat;
		}
		catch(Exception e) {
			return null;
		}
	}
}

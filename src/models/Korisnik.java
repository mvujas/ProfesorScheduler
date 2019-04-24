package models;

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
}

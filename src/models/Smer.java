package models;

public class Smer {
	private Integer id;
	private String naziv;
	
	public Smer(Integer id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	public Smer() {
		this(null, null);
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
	
}

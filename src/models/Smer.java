package models;

import java.util.Map;

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
	
	public static Smer fromRowMap(Map<String, Object> mapa) {
		Smer smer = new Smer();
		smer.setId((int)mapa.get("s_id")); // Ovo bi trebalo da baci neki NullPointerException ako ne posotji
		smer.setNaziv((String)mapa.get("naziv"));
		return smer;
	}
	
	@Override
	public String toString() {
		return getNaziv();
	}
}

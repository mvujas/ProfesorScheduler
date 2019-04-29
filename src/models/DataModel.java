package models;

public class DataModel {
	private Uloga uloga = null;

	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
	public void close() {
		System.out.println("I'll be back");
	}
	
	
}

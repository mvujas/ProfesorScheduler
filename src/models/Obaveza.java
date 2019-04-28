package models;

public class Obaveza {
	private Integer id;
	private String naziv;
	private Interval vreme;
	private Student student;
	
	public Obaveza(Integer id, String naziv, Interval vreme, Student student) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.vreme = vreme;
		this.student = student;
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
	public Interval getVreme() {
		return vreme;
	}
	public void setVreme(Interval vreme) {
		this.vreme = vreme;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}

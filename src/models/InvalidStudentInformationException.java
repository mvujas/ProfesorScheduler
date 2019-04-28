package models;

@SuppressWarnings("serial")
public class InvalidStudentInformationException extends Exception {
	private String razlog;
	
	public InvalidStudentInformationException(String razlog) {
		super(razlog);
		this.razlog = razlog;
	}
	
	public String getRazlog() {
		return this.razlog;
	}
}

package database.transactions;

import models.Student;

public class AddStudentTransaction extends DMLTransaction {
	public AddStudentTransaction(Student student) {
		super("INSERT INTO Student(broj_indeksa, ime, prezime, smer_s_id) "
				+ "VALUES (?, ?, ?, ?)", 
				student.getBrojIndeksa(),
				student.getIme(),
				student.getPrezime(),
				student.getSmer().getId());
	}
}

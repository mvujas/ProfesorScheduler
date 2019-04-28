package database.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import models.Student;

public class InsertStudentTransaction implements Transaction<Boolean> {
	private Student student;
	
	public InsertStudentTransaction(Student student) {
		if(student == null) {
			throw new NullPointerException("Student ne sme biti null");
		}
		this.student = student;
	}
	
	@Override
	public Boolean run(Connection conn) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Student(broj_indeksa, ime, prezime, smer_s_id) "
				+ "VALUES (?, ?, ?, ?)");
		stmt.setString(1, student.getBrojIndeksa());
		stmt.setString(2, student.getIme());
		stmt.setString(3, student.getPrezime());
		if(student.getSmer() == null) {
			stmt.setNull(4, Types.INTEGER);
		}
		else {
			stmt.setInt(4, student.getSmer().getId());
		}
		return stmt.executeUpdate() > 0;
	}
	
}

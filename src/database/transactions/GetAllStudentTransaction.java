package database.transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import models.Student;

public class GetAllStudentTransaction extends SelectTransaction<List<Student>> {

	public GetAllStudentTransaction() {
		super("SELECT broj_indeksa, ime, prezime, smer_s_id FROM Student");
	}

	@Override
	public List<Student> run(Connection conn) throws SQLException {
		return prepareAndRun(conn).stream().map(Student::fromRowMap).collect(Collectors.toList());
	}

}

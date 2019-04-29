package database.transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import models.Obaveza;

public class GetAllObavezeTransaction extends SelectTransaction<List<Obaveza>> {
	public GetAllObavezeTransaction() {
		super("SELECT o_id, naziv, vreme_pocetka, vreme_kraja, student_broj_indeksa "
				+ "FROM Obaveza ORDER BY vreme_pocetka ASC");
	}

	@Override
	public List<Obaveza> run(Connection conn) throws SQLException {
		return prepareAndRun(conn).stream().map(Obaveza::fromRowMap).collect(Collectors.toList());
	}
	
	
}

package database.transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import models.Smer;

public class GetAllSmerTransaction extends SelectTransaction<List<Smer>> {

	public GetAllSmerTransaction() {
		super("SELECT s_id, naziv FROM Smer");
	}

	@Override
	public List<Smer> run(Connection conn) throws SQLException {
		return prepareAndRun(conn).stream().map(Smer::fromRowMap).collect(Collectors.toList());
	}

}

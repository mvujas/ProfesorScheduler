package database;

import java.sql.Connection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import database.transactions.GetAllSmerTransaction;
import database.transactions.GetAllStudentTransaction;
import database.transactions.SelectTransaction;
import models.Smer;
import models.Student;

public class SQLDataManager implements DataManager {
	
	private ConnectionPool connections;

	public SQLDataManager(String dbName) {
		connections = new ConnectionPool("jdbc:sqlite:" + dbName);
	}
		
	@Override
	public void close() {
		connections.close();
	}
	
	private <V> V selectQuery(SelectTransaction<V> transaction) {
		Connection conn = null;
		try {
			conn = connections.getConnection();
			return transaction.run(conn);
		} catch (Exception e) {
			return null;
		} finally {
			connections.releaseConnection(conn);
		}
	}

	@Override
	public Map<Integer, Smer> getAllSmer() {
		return selectQuery(new GetAllSmerTransaction())
				.stream()
				.collect(Collectors.toMap(Smer::getId, Function.identity()));
	}

	// Konektovanje studenata i smerova se radi u modelu
	@Override
	public Map<String, Student> getAllStudent() {
		return selectQuery(new GetAllStudentTransaction())
				.stream()
				.collect(Collectors.toMap(Student::getBrojIndeksa, Function.identity()));
	}

	
}

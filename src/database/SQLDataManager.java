package database;

import java.sql.Connection;

import database.transactions.SelectTransaction;

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

	
}

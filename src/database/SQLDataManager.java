package database;

public class SQLDataManager implements DataManager {
	
	private ConnectionPool connections;

	public SQLDataManager(String dbName) {
		connections = new ConnectionPool("jdbc:sqlite:" + dbName);
	}
		
	public void close() {
		connections.close();
	}

	
}

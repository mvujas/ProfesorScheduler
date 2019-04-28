package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {
	private String url;
	private String user;
	private String password;
	private int initialSize = POOL_SIZE;
	private Set<Connection> connectionPool = null;
	private Set<Connection> connectionsInUsage = null;
	
	public ConnectionPool(String url, String user, String password) {
		setUrl(url);
		setUser(user);
		setPassword(password);
	}
	
	public ConnectionPool(String url) {
		this(url, null, null);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setInitialSize(int initialSize) {
		this.initialSize = Math.max(0, initialSize);
	}
	
	private void initPool() {
		connectionPool = new HashSet<>();
		connectionsInUsage = new HashSet<>();
		for(int i = 0; i < initialSize; i++) {
			addConnection();
		}
	}
	
	private void addConnection() {
		if(url == null) {
			throw new IllegalArgumentException("Konekcija mora imati bar URL");
		}
		
		try {
			Connection conn;
			if(user == null || password == null) {
				conn = DriverManager.getConnection(url);
			}
			else {
				conn = DriverManager.getConnection(url, user, password);
			}
			
			connectionPool.add(conn);
		}
		catch(SQLException e) {
			throw new RuntimeException("Greska prilikom konektovanja na bazu");
		}
	}

	synchronized public Connection getConnection() {
		if(connectionPool == null) {
			initPool();
		}
		
		if(connectionPool.isEmpty()) {
			addConnection();	
		}
		
		Connection conn = connectionPool.iterator().next();
		connectionPool.remove(conn);
		
		try {
			if(conn.isClosed()) {
				return getConnection();
			}
		} catch (SQLException e) {}
		
		connectionsInUsage.add(conn);
		return conn;
	}
	
	public void releaseConnection(Connection conn) {
		if(conn == null) {
			return;
		}
		
		if(connectionPool == null) {
			initPool();
		}
		
		if(!connectionsInUsage.contains(conn)) {
			return;
		}
		
		connectionsInUsage.remove(conn);
		connectionPool.add(conn);
	}

	public void close() {
		if(connectionPool == null) {
			return;
		}
		
		for(Connection conn: connectionPool) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		
		for(Connection conn: connectionsInUsage) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
	private static final int POOL_SIZE = 5;
}

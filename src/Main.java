import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.ConnectionPool;

public class Main {

	public static void main(String[] args) {
		try {
			ConnectionPool ds = new ConnectionPool("jdbc:sqlite:db.db");
			
			for(int i = 0; i < 100; i++) {
				Connection conn = ds.getConnection();
				try(Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery("SELECT * FROM Korisnik")) {
					while(rset.next()) {
						System.out.printf(
								"%d %s %s\n",
								rset.getInt("id"),
								rset.getString("ime"),
								rset.getString("prezime"));
					}
				}
				ds.releaseConnection(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

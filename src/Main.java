import java.sql.Connection;

import database.ConnectionPool;
import database.DataManager;
import database.SQLDataManager;
import database.transactions.KorisnikTransactions.*;

public class Main {

	public static void main(String[] args) {
		try {
			DataManager db = new SQLDataManager("db.db");
			System.out.println(db.getAllProfesor());
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

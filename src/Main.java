import database.DataManager;
import database.SQLDataManager;

public class Main {

	public static void main(String[] args) {
		try {
			DataManager db = new SQLDataManager("db.db");
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

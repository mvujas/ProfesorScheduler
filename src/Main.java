import database.DataManager;
import database.SQLDataManager;
import models.Interval;
import models.NevalidanIntervalException;
import utils.StudentUtils;

public class Main {

	public static void main(String[] args) {
		try {
			DataManager db = new SQLDataManager("db.db");
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(Interval.doOverlap(
					new Interval(-1, 200000000),
					new Interval(0, 100000000)));
		} catch (NevalidanIntervalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

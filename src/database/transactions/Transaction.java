package database.transactions;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction<V> {
	V run(Connection conn) throws SQLException;
}

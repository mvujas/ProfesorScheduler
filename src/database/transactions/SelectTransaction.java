package database.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import utils.DBUtils;

public abstract class SelectTransaction<V> implements Transaction<V> {

	private String query;
	private Object[] parameters;
	
	public SelectTransaction(String query, Object... parameters) {
		this.query = query;
		this.parameters = parameters;
	}
	
	public List<Map<String, Object>> prepareAndRun(Connection conn)
			throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		for(int i = 1; i <= parameters.length; i++) {
			DBUtils.setParameter(pstmt, i, parameters[i]);
		}
		ResultSet rset = pstmt.executeQuery();
		List<Map<String, Object>> result = DBUtils.resultSetToListOfMaps(rset);
		pstmt.close();
		return result;
	}
	
}

package database.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DBUtils;

public abstract class DMLTransaction implements Transaction<Integer> {
	private String query;
	private Object[] parameters;
	
	public DMLTransaction(String query, Object... parameters) {
		this.query = query;
		this.parameters = parameters;
	}
	
	@Override
	public Integer run(Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		for(int i = 1; i <= parameters.length; i++) {
			DBUtils.setParameter(pstmt, i, parameters[i - 1]);
		}
		int rowsAffected = pstmt.executeUpdate();
		pstmt.close();
		return rowsAffected;
	}
}

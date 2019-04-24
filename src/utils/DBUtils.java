package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DBUtils {
	private DBUtils() {}
	
	private static Object getColumnValue(ResultSet rset, ResultSetMetaData metaData, int column) 
			throws SQLException {
		Object result = null;
		switch(metaData.getColumnType(column)) {
			case Types.INTEGER:
				result = rset.getInt(column);
				break;
			case Types.VARCHAR:
				result = rset.getString(column);
				break;
			case Types.DATE:
				result = rset.getDate(column);
				break;
		}
		return result;
	}
	
	public static List<Map<String, Object>> resultSetToListOfMaps(ResultSet rset) {
		if(rset == null) {
			throw new IllegalArgumentException("ResultSet mustn't be null");	
		}
		
		try {
			List<Map<String, Object>> listaMapa = new LinkedList<>();
			ResultSetMetaData metaData = rset.getMetaData();
			int columnCount = metaData.getColumnCount();
			Map<String, Object> mapa;
			while(rset.next()) {
				mapa = new HashMap<>();
				for(int i = 0; i < columnCount; i++) {
					int column = i + 1;
					String imeKolone = metaData.getColumnName(column);
					Object vrednost = getColumnValue(rset, metaData, column);
					
					if(vrednost != null) {
						mapa.put(imeKolone, vrednost);
					}
				}
				listaMapa.add(mapa);
			}
			return listaMapa;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

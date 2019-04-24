package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DBUtils {
	private DBUtils() {}
	
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
					String imeKolone = metaData.getColumnName(i);
					Object vrednost = null;
					switch(metaData.getColumnType(i)) {
						case Types.INTEGER:
							vrednost = rset.getInt(i);
							break;
						case Types.VARCHAR:
							vrednost = rset.getString(i);
							break;
						case Types.DATE:
							vrednost = rset.getDate(i);
							break;
					}
					if(vrednost != null) {
						mapa.put(imeKolone, vrednost);
					}
				}
				listaMapa.add(mapa);
				
			}
			
			return listaMapa;
		} catch (Exception e) {
			return null;
		}
	}
}

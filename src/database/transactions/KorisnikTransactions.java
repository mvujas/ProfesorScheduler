package database.transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import models.Korisnik;

public final class KorisnikTransactions {
	private KorisnikTransactions() {}
	
	public static abstract class GetAllKorisnikTransaction extends SelectTransaction<List<Korisnik>> {
		public GetAllKorisnikTransaction(String nazivUloge) {
			super(String.format(
					"SELECT k.k_id, k.ime, k.prezime, u.naziv AS 'naziv_uloge' "
					+ "FROM Korisnik k, Uloga u "
					+ "WHERE u.naziv='%s' AND k.uloga_id=u.u_id", nazivUloge));
		}
		
		public List<Korisnik> run(Connection conn) throws SQLException {
			return prepareAndRun(conn).stream().map(Korisnik::fromDBRowMap).collect(Collectors.toList());
		}
	}
	
	public static class GetAllProfesorTransaction extends GetAllKorisnikTransaction {
		public GetAllProfesorTransaction() {
			super("profesor");
		}
	}
	
	public static class GetAllStudentTransaction extends GetAllKorisnikTransaction {
		public GetAllStudentTransaction() {
			super("student");
		}
	}
}

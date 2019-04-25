package database.transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import models.Korisnik;

public final class KorisnikTransactions {
	private KorisnikTransactions() {}
	
	private static abstract class GetAllKorisnik extends SelectTransaction<Korisnik> {
		public GetAllKorisnik(String nazivUloge) {
			super(String.format(
					"SELECT k.k_id, k.ime, k.prezime, u.naziv AS 'naziv_uloge' "
					+ "FROM Korisnik k, Uloga u "
					+ "WHERE u.naziv='%s' AND k.uloga_id=u.u_id", nazivUloge));
		}
		
		public List<Korisnik> run(Connection conn) throws SQLException {
			return prepareAndRun(conn).stream().map(Korisnik::fromDBRowMap).collect(Collectors.toList());
		}
	}
	
	public static class GetAllProfesor extends GetAllKorisnik {
		public GetAllProfesor() {
			super("profesor");
		}
	}
	
	public static class GetAllStudent extends GetAllKorisnik {
		public GetAllStudent() {
			super("student");
		}
	}
}

package database.transactions;

import models.Obaveza;
import utils.DateUtils;

public class AddObavezaTransaction extends DMLTransaction {
	public AddObavezaTransaction(Obaveza obaveza) {
		super("INSERT INTO Obaveza(o_id, naziv, vreme_pocetka, vreme_kraja, student_broj_indeksa) "
				+ "VALUES (?, ?, ?, ?, ?)",
				obaveza.getId(),
				obaveza.getNaziv(),
				(int)DateUtils.localDateTimeToUTCTimestamp(obaveza.getVremePocetka().minusHours(1)),
				(int)DateUtils.localDateTimeToUTCTimestamp(obaveza.getVremeKraja().minusHours(1)),
				obaveza.getStudent() == null ? null : obaveza.getStudent().getBrojIndeksa());
	}
}

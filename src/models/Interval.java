package models;

import java.util.Date;

import utils.DateUtils;

public class Interval {
	private Date pocetak;
	private Date kraj;
	
	public Interval(Date pocetak, Date kraj) 
			throws NevalidanIntervalException, NullPointerException {
		if(pocetak == null || kraj == null) {
			throw new NullPointerException(
					"Pocetka ni kraj intervala ne mogu biti null");
		}
		this.pocetak = pocetak;
		this.kraj = kraj;
		checkValidity();
	}
	
	public Interval(int pocetak, int kraj) 
			throws NevalidanIntervalException {
		this(DateUtils.intToDate(pocetak), 
				DateUtils.intToDate(kraj));
	}
	
	public Date getPocetak() {
		return pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void checkValidity() 
			throws NevalidanIntervalException {
		if(pocetak.after(kraj)) {
			throw new NevalidanIntervalException();
		}
	}
	
	public static boolean doOverlap(Interval interval1, Interval interval2) {
		return interval1.pocetak.before(interval2.kraj) &&
				interval2.pocetak.before(interval1.kraj);
	}
}

package application;

import models.Obaveza;

public class Main {
	public static void main(String[] args) {
		
		Obaveza o = new Obaveza();
		o.setVremeKraja(-3600);
		o.setVremePocetka(10000000);
		System.out.println(o);
		
		javafx.application.Application.launch(
				Application.class, args);
	}
}

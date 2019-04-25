package database;

import java.util.List;

import models.Korisnik;

public interface DataManager {
	default void close() {}
	
	List<Korisnik> getAllProfesor();
	List<Korisnik> getAllStudent();
}

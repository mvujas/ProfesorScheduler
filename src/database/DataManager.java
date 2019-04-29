package database;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import models.Obaveza;
import models.Smer;
import models.Student;

public interface DataManager {
	default void close() {}
	
	Map<Integer, Smer> getAllSmer();
	Map<String, Student> getAllStudent();
	Map<LocalDate, List<Obaveza>> getAllObaveze();
}

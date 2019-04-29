package database;

import java.util.Map;

import models.Smer;
import models.Student;

public interface DataManager {
	default void close() {}
	
	Map<Integer, Smer> getAllSmer();
	Map<String, Student> getAllStudent();
}

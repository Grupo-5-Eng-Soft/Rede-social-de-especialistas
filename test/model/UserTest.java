package model;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

	@Test
	public void getSpecialistTest() {
		Specialty specialty = new Specialty();
		specialty.setId(1);
		specialty.setName("Archimedes");
		
		User user = new User();
		user.setId(1);

		Specialist specialist = new Specialist();
		specialist.setSpecialty(specialty);
		specialist.setUser(user);
		
		ArrayList<Specialist> specialists = new ArrayList<Specialist>();
		specialists.add(specialist);
		user.setSpecialists(specialists);
		
		Specialty newSpecialty = new Specialty();
		newSpecialty.setId(1);
		newSpecialty.setName("Archimedes");
		
		Specialty newSpecialty2 = new Specialty();
		newSpecialty2.setId(2);
		newSpecialty2.setName("Linux");
		
		assertNotNull(user.getSpecialistAt(newSpecialty));
		assertNull(user.getSpecialistAt(newSpecialty2));
		
	}
	
}

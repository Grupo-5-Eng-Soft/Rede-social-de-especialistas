package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

public class UserTest {

	@Test
	public void getSpecialistTest() {
		Specialty archimedes = specialtyWithNameAndId("Archimedes", 1);
		
		User user = new User();
		user.setId(1);

		Specialist specialist = new Specialist();
		specialist.setSpecialty(archimedes);
		specialist.setUser(user);
		
		ArrayList<Specialist> specialists = new ArrayList<Specialist>();
		specialists.add(specialist);
		user.setSpecialists(specialists);
		
		Specialty otherArchimedes = specialtyWithNameAndId("Archimedes", 1);
		Specialty linux = specialtyWithNameAndId("Linux", 2);
		
		assertNotNull(user.getSpecialistAt(otherArchimedes));
		assertNull(user.getSpecialistAt(linux));
		
	}

	private Specialty specialtyWithNameAndId(String name, int id) {
		Specialty specialty = new Specialty();
		specialty.setId(id);
		specialty.setName(name);
		return specialty;
	}
	
}

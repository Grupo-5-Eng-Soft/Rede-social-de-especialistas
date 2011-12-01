package infra;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.Role;
import model.Specialist;
import model.Specialty;
import model.User;

import org.junit.Before;
import org.junit.Test;

public class UserSessionTest {

	private UserSession session;

	@Before
	public void setUp() throws Exception {
		session = new UserSession();
	}
	
	@Test
	public void shouldLogUser() throws Exception {
		User user = new User();
		user.setId(1l);
		
		session.login(user);
		
		assertTrue(user.equals(session.getLoggedUser()));
	}
	
	@Test
	public void shouldNotBeAdminIfUserIsNotLoggedIn() throws Exception {
		assertFalse(session.isAdmin());
	}
	
	@Test
	public void shouldVerifyIfUserIsAdminWhenItsNotNull() throws Exception {
		User user = new User();
		user.setRole(Role.ADMIN);
		session.login(user);
		
		assertTrue(session.isAdmin());
	}
	
	@Test
	public void shouldNotBeAuthenticatedWhenUserIsNotLoggedIn() throws Exception {
		
		assertFalse(session.isAuthenticated());
	}
	
	@Test
	public void shouldBeAuthenticatedWhenUserIsNotNull() throws Exception {
		User user = new User();
		session.login(user);
		
		assertTrue(session.isAuthenticated());
	}
	
	@Test
	public void shouldNotBeAnSpecialistIfUserIsNotAuthenticated() throws Exception {
		Specialty vraptor = new Specialty();
		
		assertFalse(session.isSpecialistIn(vraptor));
	}
	
	@Test
	public void shouldCheckSpecialtyWhenUserIsLoggedIn() throws Exception {
		Specialty vraptor = aSpecialtyWithId(1l);
		Specialty jpa = aSpecialtyWithId(2l);
		
		Specialty uml = aSpecialtyWithId(3l);
		
		User user = anUserSpecialistIn(vraptor, jpa);
		
		session.login(user);
		
		assertTrue(session.isSpecialistIn(vraptor));
		assertTrue(session.isSpecialistIn(jpa));
		
		assertFalse(session.isSpecialistIn(uml));
	}

	private Specialty aSpecialtyWithId(long id) {
		Specialty specialty = new Specialty();
		specialty.setId(id);
		return specialty;
	}
	
	private User anUserSpecialistIn(Specialty... specialties) {
		User user = new User();
		ArrayList<Specialist> specialists = new ArrayList<Specialist>();

		for (Specialty specialty : specialties) {
			Specialist specialist = new Specialist();
			specialist.setSpecialty(specialty);
			specialists.add(specialist);
		}
		
		user.setSpecialists(specialists);
		return user;
	}
}

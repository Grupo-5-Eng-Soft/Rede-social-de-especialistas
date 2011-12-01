package dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import model.Specialty;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserDaoTest {
	
	private UserDao dao;
	private @Mock Session session;
	private @Mock Transaction tx;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(session.beginTransaction()).thenReturn(tx);
		dao = new UserDao(session);
	}
	
	@Test
	public void shouldSaveUserAndCommitTransaction() throws Exception {
		User user = new User();
		dao.save(user);
		verify(session).save(user);
		verify(tx).commit();
	}
	
	@Test
	public void shouldSaveUserWithSpecialty() throws Exception {
		User user = new User();
		List<Long> specialties = mockSpecialtyWithId(1l);
		
		dao.save(user, specialties);
		
		verify(session).save(user);
		verify(session).update(user);
		verify(tx).commit();
		
		assertEquals(1, user.getSpecialists().size());
	}
	
	private List<Long> mockSpecialtyWithId(long id) {
		List<Long> specialties = new ArrayList<Long>();
		specialties.add(id);
		Specialty specialty = mock(Specialty.class);
		when(session.get(Specialty.class, id)).thenReturn(specialty);
		return specialties;
	}
}

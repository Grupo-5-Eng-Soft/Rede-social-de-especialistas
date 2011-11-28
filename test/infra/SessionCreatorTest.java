package infra;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SessionCreatorTest {

	private @Mock SessionFactory factory;
	private @Mock Session session;
	private SessionCreator creator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		creator = new SessionCreator(factory);
		when(factory.openSession()).thenReturn(session);
	}
	
	@Test
	public void shouldOpenSession() throws Exception {
		creator.abre();
		verify(factory).openSession();
	}
	
	@Test
	public void shouldCloseSession() throws Exception {
		creator.abre();
		creator.fecha();
		assertFalse(session.isOpen());
	}
}

package infra;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SessionFactoryCreatorTest {

	private SessionFactoryCreator factoryCreator;

	@Before
	public void setUp() throws Exception {
		factoryCreator = new SessionFactoryCreator();
	}
	
	@Test
	public void shouldCreateFactory() {
		assertTrue(factoryCreator.getInstance() == null);
		factoryCreator.abre();
		assertTrue(factoryCreator.getInstance() != null);
		assertFalse(factoryCreator.getInstance().isClosed());
	}
	
	@Test
	public void shouldCloseFactory() {
		factoryCreator.abre();
		factoryCreator.fecha();
		assertTrue(factoryCreator.getInstance().isClosed());
	}
}

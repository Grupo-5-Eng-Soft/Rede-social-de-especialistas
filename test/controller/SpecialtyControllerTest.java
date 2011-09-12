package controller;

import static org.mockito.Mockito.verify;
import model.Specialty;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.SpecialtyDao;

public class SpecialtyControllerTest {
	
	private @Mock SpecialtyDao dao;
	private Result result = new MockResult();
	private SpecialtyController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new SpecialtyController(result, dao);
	}

	@Test
	public void shouldSaveUser() throws Exception {
		Specialty s = new Specialty();
		s.setName("vraptor");
		controller.save(s);
		verify(dao).save(s);
	}

}

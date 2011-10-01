package controller;

import static org.mockito.Mockito.verify;
import infra.UserSession;
import model.Question;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.QuestionDao;

public class QuestionControllerTest {
	
	private @Mock QuestionDao dao;
	private Result result = new MockResult();
	private QuestionController controller;
	private UserSession session = new UserSession();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new QuestionController(result, dao, session);
	}

	@Test
	public void shouldSaveSpecialty() throws Exception {
		Question q = new Question();
		q.setTitle("Uma duvida muito importante");
		controller.save(q, 1L);
		verify(dao).save(q);
	}

}

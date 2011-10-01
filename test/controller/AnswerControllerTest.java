package controller;

import infra.UserSession;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.AnswerDao;

public class AnswerControllerTest {
	
	private @Mock AnswerDao dao;
	private Result result = new MockResult();
	private AnswerController controller;
	private UserSession session = new UserSession();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new AnswerController(result, dao, session);
	}

}

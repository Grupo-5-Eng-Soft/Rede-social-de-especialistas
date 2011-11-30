package controller;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import infra.UserSession;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.QuestionDao;
import dao.UserDao;

public class IndexControllerTest {
	private UserSession session = new UserSession();
	private MockResult result = new MockResult();
	private @Mock UserDao userdao;
	private @Mock QuestionDao questiondao;
	private IndexController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(result, session, userdao, questiondao);
	}
	
	@Test
	public void shouldIncludeSpecialistsAndQuestionsAtInitialPage() {
		controller.index();
		result.included("specialists");
		result.included("questions");
	}
	
}

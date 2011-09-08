package controller;

import static org.mockito.Mockito.verify;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.EmailConfirmationDao;
import dao.UserDao;

public class EmailConfirmationControllerTest {
	
	private @Mock EmailConfirmationDao dao;
	private Result result = new MockResult();
	private EmailConfirmationController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new EmailConfirmationController(result, dao);
	}

	@Test
	public void shouldSaveUser() throws Exception {
		User user = validUser();
		controller.createAndSendEmailConfirmation(user);
		verify(dao).saveEmailConfirmationFromUser(user);
	}

	private User validUser() {
		User user = new User();
		user.setEmail("teste@blabla.bla");
		user.setLogin("teste");
		user.setPassword("teste1234");
		return user;
	}
}

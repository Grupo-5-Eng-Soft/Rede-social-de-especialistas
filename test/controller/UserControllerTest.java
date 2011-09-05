package controller;

import static org.mockito.Mockito.verify;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.UserDao;

public class UserControllerTest {
	
	private @Mock UserDao dao;
	private Result result = new MockResult();
	private UserController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new UserController(result, dao);
	}

	@Test
	public void shouldSaveUser() throws Exception {
		User user = validUser();
		controller.save(user);
		verify(dao).save(user);
	}

	private User validUser() {
		User user = new User();
		user.setEmail("teste@blabla.bla");
		user.setLogin("teste");
		user.setPassword("teste1234");
		return user;
	}
}

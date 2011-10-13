package controller;

import static org.mockito.Mockito.verify;
import infra.UserSession;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import dao.UserDao;

public class UserControllerTest {
	
	private @Mock UserDao dao;
	private Result result = new MockResult();
	private Validator validator = new MockValidator();
	private UserController controller;
	private UserSession userSession;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new UserController(result, validator, dao, userSession);
	}

	@Test
	public void shouldSaveUser() throws Exception {
		User user = validUser();
		controller.save(user, null);
		verify(dao).save(user, null);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldValidateUser() throws Exception {
		User user = validUser();
		user.setEmail("email-invalido");
		controller.save(user, null);
	}

	private User validUser() {
		User user = new User();
		user.setEmail("teste@blabla.bla");
		user.setLogin("teste");
		user.setPassword("teste1234");
		return user;
	}
}

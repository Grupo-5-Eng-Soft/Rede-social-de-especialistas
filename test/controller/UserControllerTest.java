package controller;

import static br.com.caelum.vraptor.view.Results.http;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import hash.HashCalculator;
import infra.UserSession;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

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
import br.com.caelum.vraptor.view.HttpResult;
import dao.UserDao;

public class UserControllerTest {
	
	private @Mock UserDao dao;
	private @Mock Result mockResult;
	private Result result = new MockResult();
	private Validator validator = new MockValidator();
	private UserController controller;
	private @Mock UserSession userSession;
	private @Mock HttpResult http;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new UserController(result, validator, dao, userSession);
		when(mockResult.use(http())).thenReturn(http);
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

	@Test
	public void shouldNotAllowToEditOtherUser() {
		controller = new UserController(mockResult, validator, dao, userSession);
		
		User loggedUser = validUser();
		User otherUser = validUser();
		otherUser.setEmail("outroemail@gmail.com");
		otherUser.setId(666);
		stubMockedUserSession(loggedUser);
		controller.userEditForm(otherUser.getId());
		verify(http).sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
	@Test
	public void shouldContinueActiveWhenEmailWasNotEdited() {
		User loggedUser = validUser();
		User editedUser = validUser();
		editedUser.setName(loggedUser.getName() + " mais um sobrenome");
		
		stubMockedUserSession(loggedUser);
		// quando buscar pelo email, deve retornar o usuario nao editado
		when(dao.getUserByEmail(editedUser.getEmail())).thenReturn(loggedUser);
		
		controller.saveEdit(editedUser, new ArrayList<Long>());
		verify(dao).edit(editedUser, new ArrayList<Long>());
		assertEquals(true, editedUser.isActive());
	}
	
	@Test
	public void shouldNotContinueActiveWhenEmailWasEdited() {
		User loggedUser = validUser();
		User editedUser = validUser();
		loggedUser.setEmail("original@gmail.com");
		editedUser.setEmail("novo@gmail.com");
		
		stubMockedUserSession(loggedUser);
		// quando buscar pelo email, deve retornar null, pois nao existia no bd
		when(dao.getUserByEmail(editedUser.getEmail())).thenReturn(null);
		
		controller.saveEdit(editedUser, new ArrayList<Long>());
		verify(dao).edit(editedUser, new ArrayList<Long>());
		assertEquals(false, editedUser.isActive());
	}
	
	@Test
	public void shouldAuthenticate() {
		User user = setupUserToauthenticate();
		
		controller.authenticate(user.getLogin(), "teste1234");
		verify(userSession).login(user);
	}
	
	@Test
	public void shouldNotAuthenticateWhenIsNotActive() {
		result = mock(Result.class);
		controller = new UserController(result, validator, dao, userSession);
		User user = setupUserToauthenticate();
		user.setActive(false);
		
		UserController spy = spy(controller);  
		when(result.redirectTo(UserController.class)).thenReturn(spy);
		controller.authenticate(user.getLogin(), "teste1234");
		
		verify(spy).loginForm();
	}
	
	@Test
	public void shouldNotAuthenticateWithWrongPassword() {
		result = mock(Result.class);
		controller = new UserController(result, validator, dao, userSession);
		User user = setupUserToauthenticate();
		user.setActive(true);
		
		UserController spy = spy(controller);  
		when(result.redirectTo(UserController.class)).thenReturn(spy);
		controller.authenticate(user.getLogin(), "essa senha esta errada");
		
		verify(spy).loginForm();
	}

	private User setupUserToauthenticate() {
		User user = validUser();
		user.setActive(true);
		HashCalculator encryption = new HashCalculator("teste1234");
		String password = encryption.getValue();
		user.setPassword(password);
		
		// quando buscar pelo login deve retornar o usuario que quer logar
		when(dao.getUser(user.getLogin())).thenReturn(user);
		return user;
	}
	
	private User validUser() {
		User user = new User();
		user.setId(1);
		user.setEmail("teste@blabla.bla");
		user.setLogin("teste");
		HashCalculator encryption = new HashCalculator("teste1234");
		String password = encryption.getValue();
		user.setPassword(password);
		//user.setActive(true);
		return user;
	}
	
	private void stubMockedUserSession(User user) {
		when(userSession.getLoggedUser()).thenReturn(user);
	}
	
}

package controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import hash.HashCalculator;
import infra.UserSession;
import model.User;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.InterceptionException;
import dao.UserDao;

public class UserControllerTest {
	
	private @Mock UserDao dao;
	private Result result = new MockResult();
	private Validator validator = new MockValidator();
	private UserController controller;
	private @Mock UserSession userSession;

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

	@Test
	public void shouldNotAllowToEditOtherUser() {
		result = mock(Result.class);
		controller = new UserController(result, validator, dao, userSession);
		User loggedUser = validUser();
		User otherUser = validUser();
		otherUser.setEmail("outroemail@gmail.com");
		otherUser.setId(666);
		stubMockedUserSession(loggedUser);
		when(result.redirectTo(ErrorController.class)).thenReturn(new ErrorController());
		controller.userEditForm(otherUser.getId());
		verify(result).redirectTo(ErrorController.class);
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

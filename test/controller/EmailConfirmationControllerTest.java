package controller;

import static org.mockito.Mockito.*;
import infra.EmailSender;
import hash.HashCalculator;
import model.EmailConfirmation;
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
	private @Mock UserDao users;
	private @Mock EmailSender emailSender;
	private Result result = new MockResult();
	private EmailConfirmationController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new EmailConfirmationController(result, dao, users, emailSender);
	}

	@Test
	public void shouldSaveUser() throws Exception {
		User user = validUser();
		controller.createAndSendEmailConfirmation(user, null, false);
		verify(dao).saveEmailConfirmationFromUser(user, false);
	}
	
	@Test
	public void shouldConfirmUser() {
		User user = validUser();
		user.setActive(false);
		HashCalculator hash = new HashCalculator(user.getLogin() + user.getEmail());
		String confirmationString = hash.getValue();
		EmailConfirmation confirmation = new EmailConfirmation();
		confirmation.setUser(user);
		when(users.getUser(user.getId())).thenReturn(user);
		when(dao.getEmailConfirmation(user)).thenReturn(confirmation);
		controller.confirmUser(user.getId(), confirmationString);
		verify(dao).getEmailConfirmation(user);
	}
	
	@Test
	public void shouldNotConfirmUserWithInvalidID() {
		when(users.getUser(666)).thenReturn(null);
		controller.confirmUser(666, "hashqualquer");
		verify(users).getUser(666);
	}
	
	@Test
	public void shouldNotConfirmUserWithActiveUser() {
		User user = validUser();
		user.setActive(true);
		String confirmationString = "hashqualquer";
		when(users.getUser(user.getId())).thenReturn(user);
		controller.confirmUser(user.getId(), confirmationString);
		verify(dao, never()).removeEmailConfirmationFrom(user);
	}
	
	@Test
	public void shouldNotConfirmUserWithWrongHash() {
		User user = validUser();
		user.setActive(false);
		HashCalculator hash = new HashCalculator(user.getLogin() + user.getEmail());
		String confirmationString = hash.getValue();
		EmailConfirmation confirmation = new EmailConfirmation();
		confirmation.setUser(user);
		when(users.getUser(user.getId())).thenReturn(user);
		when(dao.getEmailConfirmation(user)).thenReturn(confirmation);
		controller.confirmUser(user.getId(), "HASH ERRADA");
		verify(dao, never()).removeEmailConfirmationFrom(user);
	}

	private User validUser() {
		User user = new User();
		user.setId(1L);
		user.setEmail("grupo5.engsoft@gmail.com");
		user.setLogin("junit");
		user.setPassword("lala");
		return user;
	}
}

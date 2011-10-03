package controller;

import java.util.ArrayList;

import javax.servlet.ServletRequest;

import infra.UserSession;
import hash.HashCalculator;
import model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import dao.UserDao;


@Resource
public class UserController {
	private final Result result;
	private final UserDao dao;
	private final UserSession userSession;
	private final Validator validator;

	public UserController(Result result, Validator validator, UserDao dao, UserSession userSession) {
		this.userSession = userSession;
		this.result = result;
		this.dao = dao;
		this.validator = validator;
	}

	@Path("/usuarios/cadastrar/")
	public void userForm() {
		result.include("specialties", dao.list());
	}

	@Path("/login/")
	public void loginForm() {
	}

	@Path("/logout/")
	public void logout() {
		userSession.logout();
		result.redirectTo(IndexController.class).index();
	}

	@Post
	@Path("/login/autenticar/")
	public void authenticate(String login, String password) {
		User user = dao.getUser(login);
		if (user != null) {
			HashCalculator encryption = new HashCalculator(password);
			password = encryption.getValue();
			if (user.getPassword().equals(password) && user.isActive()) {
				userSession.login(user);
				result.redirectTo(IndexController.class).index();
			} else {
				result.include("notAuthenticated", "Usuário com cadastro não confirmado. Verifique seu e-mail.");
				result.redirectTo(UserController.class).loginForm();
			}
		} else {
			result.include("notFound", "Usuário não cadastrado no sistema.");
			result.redirectTo(UserController.class).loginForm();
		}
	}

	@Path("/usuarios/salvar/")
	public void save(User user, String confirmation, ArrayList<Long> specialties_ids) {
		if (user.getLogin().isEmpty()) {
			validator.add(new ValidationMessage("Login é obrigatório.","user.login"));
		}
		if (dao.getUser(user.getLogin()) != null) {
			validator.add(new ValidationMessage("Usuário já existente.","user.login"));
		}
		if (user.getEmail().isEmpty()) {
			validator.add(new ValidationMessage("E-mail é obrigatório.","user.email"));
		}
		if (user.getEmail().split("@").length != 2) {
			validator.add(new ValidationMessage("E-mail inválido.","user.email"));
		}
		if (dao.getUserByEmail(user.getEmail()) != null) {
			validator.add(new ValidationMessage("E-mail já existente.","user.email"));
		}
		if (user.getPassword().isEmpty()) {
			validator.add(new ValidationMessage("Senha é obrigatória.","user.password"));
		}
		if (!user.getPassword().equals(confirmation)) {
			validator.add(new ValidationMessage("Senha não confere.","user.password"));
		}
		if (user.getPassword().length() < 6) {
			validator.add(new ValidationMessage("Senha menor que 6 caracteres.","user.password"));
		}
		validator.onErrorRedirectTo(this).userForm();
		user.setActive(false);
		user.setPasswordFromRawString(user.getPassword());
		dao.save(user, specialties_ids);
		result.redirectTo(EmailConfirmationController.class).createAndSendEmailConfirmation(user);
	}

	
	@Path("/usuarios/{userId}/")
	public void detail(long userId) {
		result.include("user", dao.getUser(userId));
	}
	

}

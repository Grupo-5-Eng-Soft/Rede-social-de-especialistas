package controller;

import hash.HashCalculator;
import infra.UserSession;

import java.util.ArrayList;

import model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
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
		result.include("specialties", dao.listSpecialty());
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
			} else if (!user.isActive()){
				result.include("notAuthenticated", "Usuário com cadastro não confirmado. Verifique seu e-mail.");
				result.redirectTo(UserController.class).loginForm();
			}
			else {
				result.include("notAuthenticated", "Senha incorreta.");
				result.redirectTo(UserController.class).loginForm();
			}
		} else {
			result.include("notFound", "Usuário não cadastrado no sistema.");
			result.redirectTo(UserController.class).loginForm();
		}
	}

	@Path("/usuarios/salvar/")
	public void save(User user, ArrayList<Long> specialties_ids) {
		validateUser(user);
		user.setActive(false);
		user.setPasswordFromRawString(user.getPassword());
		dao.save(user, specialties_ids);
		result.redirectTo(EmailConfirmationController.class).createAndSendEmailConfirmation(user);
	}

	@Path("/usuarios/editar/{userId}/")
	public void userEditForm(long userId){
		result.include("user",dao.getUser(userId));
		result.include("specialties", dao.listSpecialty());
	}
	
	@Path("/usuarios/atualizacao/")
	public void saveEdit(User user, ArrayList<Long> specialties_ids) {
		copyUnchangeableFields(user);
		user.setActive(true);
		validateProfile(user);
		dao.edit(user, specialties_ids);
		userSession.getLoggedUser().setLogin(user.getLogin());
		userSession.getLoggedUser().setEmail(user.getEmail());
		result.redirectTo(IndexController.class).index();
	}

	private void copyUnchangeableFields(User user) {
		user.setPassword(userSession.getLoggedUser().getPassword());
		user.setRole(userSession.getLoggedUser().getRole());
		user.setLogin(userSession.getLoggedUser().getLogin());
		user.setId(userSession.getLoggedUser().getId());
	}
	
	@Path("/usuarios/{userId}/")
	public void detail(long userId) {
		result.include("user", dao.getUser(userId));
	}
	
	
	@Path("/usuarios/listar/")
	public void list(){
		result.include("user",dao.listUser());
	}
	
	private void validateProfile(final User user) {
		validator.checking(new Validations() {{			
			that(!user.getEmail().isEmpty(), "user.email", "email.obrigatorio");
			that(user.getEmail().split("@").length == 2, "user.email", "email.invalido");

			that(dao.getUserByEmail(user.getEmail()).getId() == user.getId(),"user.email", "email.ja.existente");
		}});
		
		validator.onErrorRedirectTo(this).userForm();
	}
	
	private void validateUser(final User user) {
		validateProfile(user);
		validator.checking(new Validations() {{
			that(!user.getLogin().isEmpty(), "user", "login.obrigatorio");

			that(!user.getPassword().isEmpty(), "user.password", "senha.obrigatoria");
			that(user.getPassword().length() >= 6, "user.password", "senha.menor.que.6.caracteres");
			
			that(dao.getUser(user.getLogin()) == null, "user.login", "usuario.ja.existente");
			that(dao.getUserByEmail(user.getEmail()) == null, "user.email", "email.ja.existente");
			}
		});

		validator.onErrorRedirectTo(this).userForm();
	}

}

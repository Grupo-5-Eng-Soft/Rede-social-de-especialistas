package controller;

import infra.UserSession;
import hash.HashCalculator;
import model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.UserDao;

@Resource
public class UserController {
	private final Result result;
	private final UserDao dao;
	private final UserSession userSession;
	
	public UserController(Result result, UserDao dao, UserSession userSession) {
		this.userSession = userSession;
		this.result = result;
		this.dao = dao;
	}
	
	@Path("/usuarios/cadastrar/")
	public void userForm() { }
	
	@Path("/login/")
	public void loginForm() { }
	
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
			System.out.println("==================");
			System.out.println("dao: "+user.getPassword());
			System.out.println("calculado: "+password);
			System.out.println("==================");
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
	public void save(User user) {
		user.setActive(false);
		user.setPasswordFromRawString(user.getPassword());
		dao.save(user);
		result.redirectTo(EmailConfirmationController.class).createAndSendEmailConfirmation(user);
	}
	
}

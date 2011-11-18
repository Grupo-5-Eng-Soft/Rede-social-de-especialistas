package controller;

import static br.com.caelum.vraptor.view.Results.http;
import hash.HashCalculator;
import infra.EmailSender;
import infra.UserSession;
import interceptor.annotations.Admin;
import interceptor.annotations.LoggedUser;
import interceptor.annotations.ModifiesUser;
import interceptor.annotations.NotSpecialist;
import interceptor.annotations.Specialist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import model.Question;
import model.User;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
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
				if (user.isSpecialist())
					result.redirectTo(UserController.class).specialistInitialPage();
				else
					result.redirectTo(UserController.class).notSpecialistInitialPage();
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
		user.setCertified(false);
		user.setPasswordFromRawString(user.getPassword());
		dao.save(user, specialties_ids);
		result.redirectTo(EmailConfirmationController.class).createAndSendEmailConfirmation(user, null, false);
	}
	
	@LoggedUser
	@Path("/usuarios/trocarsenha/")
	public void changePasswordForm() {
		
	}
	
	@LoggedUser
	@Path("/usuarios/salvarsenha/")
	public void changePasswordForm(String newPassword, String oldPassword, String confirmation) {
		User loggedUser = userSession.getLoggedUser();
		HashCalculator encryption = new HashCalculator(oldPassword);
		oldPassword = encryption.getValue();
		if (oldPassword.equals(loggedUser.getPassword()) && confirmation.equals(newPassword)) {
			User userToUpdate = dao.getUser(loggedUser.getId());
			userToUpdate.setPasswordFromRawString(newPassword);
			dao.updateUser(userToUpdate);
			result.include("notice", "Troca de senha efetuada com sucesso.");
			result.redirectTo(IndexController.class).index();
		}
		else {
			if (!oldPassword.equals(loggedUser.getPassword())) {
				result.include("error", "Senha incorreta.");
			}
			if (!confirmation.equals(newPassword)) {
				result.include("error", "Confirmação de senha incorreta.");
			}
			validator.add(new ValidationMessage("Senha incorreta", "Mudança de senha"));
			validator.onErrorRedirectTo(this).changePasswordForm();
		}
		
	}

	@ModifiesUser
	@Path("/usuarios/editar/{userId}/")
	@LoggedUser
	public void userEditForm(final long userId){
		if (userSession.getLoggedUser().getId() != userId) {
			result.use(http()).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		result.include("user", userSession.getLoggedUser());
		result.include("specialties", dao.listSpecialty());
		result.include("userSpecialties", userSession.getLoggedUser().getSpecialties());
	}
	
	@Path("/usuarios/atualizacao/")
	public void saveEdit(User user, ArrayList<Long> specialties_ids) {
		copyUnchangeableFields(user);
		validateProfile(user);
		User userByEmail = dao.getUserByEmail(user.getEmail());
		
		// o e-mail mudou e nao existia no bd
		if (userByEmail == null) {
			dao.edit(user, specialties_ids);
			userSession.logout();
			user.setActive(false);
			result.redirectTo(EmailConfirmationController.class).
			createAndSendEmailConfirmation(user, "Sua conta foi editada com sucesso," +
			" verifique a sua caixa de mensagens para confirmar a mudança do seu email.", false);
		}
		else {
			// o e-mail não mudou
			if (userByEmail.getId() == user.getId()) {
				user.setActive(true);
				dao.edit(user, specialties_ids);
				userSession.login(user);
				result.redirectTo(IndexController.class).index();
			}
			// o e-mail mudou e existia no bd
			else {
				 validator.add(new ValidationMessage("user.email", "email.ja.existente"));
				 validator.onErrorRedirectTo(this).userEditForm(user.getId());
			}
		}
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
	
	@Admin
	@Path("/usuario/certificado/{userId}")
	public void certify(Long userId) {
		User user = dao.getUser(userId);
		user.setCertified(!user.isCertified());
		dao.updateUser(user);
		result.redirectTo(UserController.class).detail(userId);
	}
	
	@Path("/usuarios/recuperarsenha/")
	public void recoverPassword() {
	}
	
	@Path("/usuarios/enviarsenha/")
	public void sendPassword(String email) {
		User user = dao.getUserByEmail(email);
		String cod = randomPassword();
		if (user != null) {
			String message = "Sua nova senha eh: " + cod + "\n\n" +
					"Aconselhamos que mude a sua senha em Editar Perfil no topo direito da pagina.\n Obrigado.";
			EmailSender emailSender = new EmailSender(email, message, "Rede Social de Especialistas - Recuperação de Senha");
			Thread emailSenderThread = new Thread(emailSender);
			emailSenderThread.start();
			user.setPasswordFromRawString(cod);
			dao.updateUser(user);
			result.include("notice", "Verifique a sua senha nova no seu email.");
			result.redirectTo(IndexController.class).index();
		}
		else {
			result.include("errorMessage", "Não foi possível encontrar um usuário com esse email. Verifique se você digitou corretamente.");
			result.redirectTo(UserController.class).recoverPassword();
		}
	}
	
	private String randomPassword() {
		 String letters = "ABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789"; 
	     Random random = new Random();
	     
	     String code = "";
	     int index = -1;
	     for( int i = 0; i < 9; i++ ) {
	         index = random.nextInt( letters.length() );
	         code += letters.substring( index, index + 1 );
	     }
	     return code;
	}
	
	@Path("/usuarios/reenviar/cadastro/")
	public void resendConfirmation() {
		
	}
	
	@Path("/usuarios/reenviar/cadastro/enviar/")
	public void sendNewConfirmation(String email) {
		User user = dao.getUserByEmail(email);
		if(user == null) {
			result.include("errorMessage", "Não foi possível encontrar um cadastro com esse email. Verifique se você digitou corretamente.");
			result.redirectTo(UserController.class).resendConfirmation();
		}
		else
			result.redirectTo(EmailConfirmationController.class).createAndSendEmailConfirmation(user, null, true);
	}

	@LoggedUser
	@Specialist
	@Path("/usuarios/inicio/especialista/")
	public void specialistInitialPage() {
		User loggedUser = userSession.getLoggedUser();
		List<Question> openQuestions = dao.getQuestionsFromSpecialties(loggedUser.getSpecialties());
		HashMap<String, List<Question>> questionsHashMapBySpecialtyName = getOpenQuestionsHashMap(openQuestions);
		result.include("questionsHashMapBySpecialtyName", questionsHashMapBySpecialtyName);
		result.include("user", loggedUser);
	}

	private HashMap<String, List<Question>> getOpenQuestionsHashMap(
			List<Question> openQuestions) {
		HashMap<String, List<Question>> questionsBySpecialtyName = new HashMap<String, List<Question>>();
		for (Question question : openQuestions) {
			String specialtyName = question.getSpecialty().getName();
			if (!questionsBySpecialtyName.containsKey(specialtyName))
				questionsBySpecialtyName.put(specialtyName, new ArrayList<Question>());
			questionsBySpecialtyName.get(specialtyName).add(question);
		}
		return questionsBySpecialtyName;
	}
	
	@LoggedUser
	@NotSpecialist
	@Path("/usuarios/inicio/comum/")
	public void notSpecialistInitialPage() {
		result.include("user", userSession.getLoggedUser());
	}

}

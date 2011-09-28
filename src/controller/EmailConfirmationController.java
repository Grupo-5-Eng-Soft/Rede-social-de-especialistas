package controller;
//8086 8123
import infra.EmailSender;
import hash.HashCalculator;
import model.EmailConfirmation;
import model.User;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.EmailConfirmationDao;
import dao.UserDao;

@Resource
public class EmailConfirmationController {
	private final Result result;
	private final EmailConfirmationDao dao;
	private final UserDao users;

	public EmailConfirmationController(Result result, EmailConfirmationDao dao, UserDao users) {
		this.result = result;
		this.dao = dao;
		this.users = users;
	}
	
	public void createAndSendEmailConfirmation(User user) {
		dao.saveEmailConfirmationFromUser(user);
		sendEmail(user);
		result.redirectTo(IndexController.class).index();
	}
	
	@Get("/usuarios/confirmar/{userId}/{hash}")
	public void confirmUser(long userId, String hash) {
		User user = users.getUser(userId);
		if (user == null) {
			result.include("message", "URL incorreta!");
			return;
		}
		if (user.isActive()) {
			result.include("message", "Sua conta já foi ativada!");
			return;
		}
		EmailConfirmation confirmation = dao.getEmailConfirmation(user);
		if (confirmation == null || !hash.equals(confirmation.getConfirmationString())){
			result.include("message", "URL incorreta!");
			return;
		}
		user.setActive(true);
		users.save(user);
		dao.removeEmailConfirmationFrom(user);
		result.include("message", "Parabéns, sua conta foi ativada!");
	}

	private void sendEmail(User user) {
		HashCalculator hashCalculator = new HashCalculator(user.getLogin() + user.getEmail());
		String hash = hashCalculator.getValue();
		String message = "Confirme sua conta em http://localhost:8080/rede-social-de-especialistas/usuarios/confirmar/"+user.getId()+"/"+hash;
		Thread emailSenderThread = new Thread(new EmailSender(user, message, "Confirme sua conta"));
		emailSenderThread.start();
	}
}

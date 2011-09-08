package controller;

import hash.HashCalculator;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import model.EmailConfirmation;
import model.User;
import dao.EmailConfirmationDao;
import dao.UserDao;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class EmailConfirmationController {
	private final Result result;
	private final EmailConfirmationDao dao;

	public EmailConfirmationController(Result result, EmailConfirmationDao dao) {
		this.result = result;
		this.dao = dao;
	}
	
	public void createAndSendEmailConfirmation(User user) {
		dao.saveEmailConfirmationFromUser(user);
		sendEmail(user);
		result.redirectTo(IndexController.class).index();
	}
	
	@Get("/usuarios/confirmar/{userId}/{hash}")
	public void confirmUser(int userId, String hash) {
		UserDao userDao = new UserDao();
		User user;
		user = userDao.getUser(userId);
		if (user == null) {
			result.include("message", "URL incorreta!");
			return;
		}
		if (user.isActive()) {
			result.include("message", "Sua conta já foi ativada!");
			return;
		}
		EmailConfirmation confirmation = dao.getEmailConfirmation(user, hash);
		if (confirmation == null || !hash.equals(confirmation.getConfirmationString())){
			result.include("message", "URL incorreta!");
			return;
		}
		user.setActive(true);
		userDao.save(user);
		result.include("message", "Parabéns, sua conta foi ativada!");
		
	}

	private void sendEmail(User user) {
		String emailAddress = user.getEmail();
		String hash = HashCalculator.calculateHash(user.getLogin());
		SimpleEmail email = new SimpleEmail();
		String message = "Confirme sua conta em http://localhost:8080/rede-social-de-especialistas/usuarios/confirmar/"+user.getId()+"/"+hash;
		email.setDebug(true);
		email.setHostName("smtp.gmail.com");
		email.setAuthentication("grupo5.engsoft","ohhappyday");
		email.setSSL(true);
		try {
			email.addTo(emailAddress);
			email.setFrom("grupo5.engsoft@gmail.com"); 
			email.setSubject("Confiramção de conta");
			email.setMsg(message);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		} 

	}
}

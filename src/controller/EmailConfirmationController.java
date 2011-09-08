package controller;

import hash.HashCalculator;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import model.EmailConfirmation;
import model.User;
import dao.EmailConfirmationDao;
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

	private void sendEmail(User user) {
		String emailAddress = user.getEmail();
		String hash = HashCalculator.calculateHash(user.getLogin());
		SimpleEmail email = new SimpleEmail();
		String message = "Confirme sua conta em http://localhost:8000/usuarios/confirmar/?login="+user.getLogin()+"&hash="+hash;
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

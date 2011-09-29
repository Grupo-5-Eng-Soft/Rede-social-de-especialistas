package infra;

import java.util.ArrayList;

import hash.HashCalculator;

import model.User;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender implements Runnable {
	
	private ArrayList<User> receivers;
	private String message;
	private String subject;
	
	
	public EmailSender(ArrayList<User> receivers, String message, String subject) {
		this.receivers = receivers;
		this.message = message;
		this.subject = subject;
	}
	
	public EmailSender(User user, String message, String subject) {
		receivers = new ArrayList<User>();
		receivers.add(user);
		this.message = message;
		this.subject = subject;
	}

	@Override
	public void run() {
		SimpleEmail email = new SimpleEmail();
		email.setDebug(true);
		email.setHostName("smtp.gmail.com");
		email.setAuthentication("grupo5.engsoft","ohhappyday");
		email.setSSL(true);
		email.setSubject(this.subject);
		//enviar para apenas um destinatario
		if (receivers.size() == 1) {
			sendEmailToOneReceiver(receivers.get(0), email);
		}
		//enviar para varios destinatarios com blind copy
		else  {
			try {
				sendEmailToMultipleReceivers(receivers, email);
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendEmailToMultipleReceivers(ArrayList<User> receivers, SimpleEmail email) throws EmailException {
		for (User user : receivers)
			email.addBcc(user.getEmail());
		email.setFrom("grupo5.engsoft@gmail.com"); 
		email.setMsg(this.message);
		email.send();

	}

	private void sendEmailToOneReceiver(User destination, SimpleEmail email) {
		String emailAddress = destination.getEmail();
		try {
			email.addTo(emailAddress);
			email.setFrom("grupo5.engsoft@gmail.com"); 
			email.setMsg(this.message);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}

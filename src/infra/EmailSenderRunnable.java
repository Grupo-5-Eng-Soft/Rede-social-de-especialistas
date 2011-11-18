package infra;

import java.util.ArrayList;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSenderRunnable implements Runnable {
	
	private ArrayList<String> receivers;
	private String message;
	private String subject;
	private String fromAddress = "grupo5.engsoft@gmail.com";
	private String authenticationPassword = "ohhappyday";
	private String authenticationUserName = "grupo5.engsoft";
	private String EmailServerHostName = "smtp.gmail.com";
	
	public EmailSenderRunnable(ArrayList<String> receivers, String message, String subject) {
		this.receivers = receivers;
		this.message = message;
		this.subject = subject;
	}
	
	public EmailSenderRunnable(String email, String message, String subject) {
		receivers = new ArrayList<String>();
		receivers.add(email);
		this.message = message;
		this.subject = subject;
	}

	@Override
	public void run() {
		SimpleEmail email = new SimpleEmail();
		email.setDebug(true);
		email.setHostName(this.EmailServerHostName);
		email.setAuthentication(this.authenticationUserName, this.authenticationPassword);
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

	private void sendEmailToMultipleReceivers(ArrayList<String> receivers, SimpleEmail email) throws EmailException {
		for (String destination : receivers)
			email.addBcc(destination);
		email.setFrom(this.fromAddress); 
		email.setMsg(this.message);
		email.send();

	}

	private void sendEmailToOneReceiver(String destination, SimpleEmail email) {
		try {
			email.addTo(destination);
			email.setFrom(this.fromAddress); 
			email.setMsg(this.message);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public void setAuthenticationPassword(String authenticationPassword) {
		this.authenticationPassword = authenticationPassword;
	}

	public void setAuthenticationUserName(String authenticationUserName) {
		this.authenticationUserName = authenticationUserName;
	}

	public void setEmailServerHostName(String emailServerHostName) {
		EmailServerHostName = emailServerHostName;
	}

}

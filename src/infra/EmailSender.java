package infra;

import java.util.ArrayList;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class EmailSender {
	
	private String fromAddress = "grupo5.engsoft@gmail.com";
	private String authenticationPassword = "ohhappyday";
	private String authenticationUserName = "grupo5.engsoft";
	private String emailServerHostName = "smtp.gmail.com";
	
	public void sendEmail(String message, String destinationAddress, String subject) {
		EmailSenderRunnable runnable = new EmailSenderRunnable(destinationAddress, message, subject);
		setUpConfigurations(runnable);
		Thread emailSenderThread = new Thread(runnable);
		emailSenderThread.start();
	}
	
	public void sendEmail(ArrayList<String> receivers, String message, String subject) {
		EmailSenderRunnable runnable = new EmailSenderRunnable(receivers, message, subject);
		setUpConfigurations(runnable);
		Thread emailSenderThread = new Thread(runnable);
		emailSenderThread.start();
	}
	
	private void setUpConfigurations(EmailSenderRunnable runnable) {
		runnable.setFromAddress(fromAddress);
		runnable.setAuthenticationPassword(authenticationPassword);
		runnable.setEmailServerHostName(emailServerHostName);
		runnable.setAuthenticationUserName(authenticationUserName);
	}
}

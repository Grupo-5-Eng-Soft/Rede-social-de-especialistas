package dao;

import model.EmailConfirmation;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmailConfirmationDao {
	private final Session session;

	public EmailConfirmationDao(Session session) {
		this.session = session;
	}
	
	public void saveEmailConfirmationFromUser(User user, boolean resend) {
		EmailConfirmation confirmation = new EmailConfirmation();
		confirmation.setUser(user);
		Transaction tx = session.beginTransaction();
		if(resend)
			session.update(confirmation);
		else
			session.save(confirmation);
		tx.commit();
	}
	
	public EmailConfirmation getEmailConfirmation(User user) {
		return (EmailConfirmation) this.session.get(EmailConfirmation.class, user.getId());
	}

	public void removeEmailConfirmationFrom(User user) {
		Transaction tx = session.beginTransaction();
		EmailConfirmation confirmation = this.getEmailConfirmation(user);
		session.delete(confirmation);
		tx.commit();
	}
	
}

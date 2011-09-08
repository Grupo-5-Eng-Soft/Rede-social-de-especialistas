package dao;

import model.EmailConfirmation;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmailConfirmationDao {
	private Session session;

	public EmailConfirmationDao() {
		this.session = SessionCreator.criaSessao();
	}
	
	public void saveEmailConfirmationFromUser(User user) {
		EmailConfirmation confirmation = new EmailConfirmation();
		confirmation.setUser(user);
		Transaction tx = session.beginTransaction();
		session.save(confirmation);
		tx.commit();
	}
	
}

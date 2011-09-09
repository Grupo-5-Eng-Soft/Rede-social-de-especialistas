package dao;

import java.util.List;

import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UserDao {
	private Session session;
	
	public UserDao() {
		this.session = SessionCreator.criaSessao();
	}
	
	public void save(User user) {
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}
	
	public User getUser(int userId) {
		return (User) this.session.get(User.class, userId);
	}
	
	public User getUser(String login) {
		return (User) this.session.createCriteria(User.class)
				.add(Restrictions.eq("login", login))
				.list().get(0);
	}
	
}

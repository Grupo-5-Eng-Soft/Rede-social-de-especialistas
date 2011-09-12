package dao;

import infra.SessionCreator;

import java.util.List;

import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class UserDao {
	private Session session;
	
	public UserDao() {
		this.session = SessionCreator.createSession();
	}
	
	public void save(User user) {
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}
	
	public User getUser(long userId) {
		return (User) this.session.get(User.class, userId);
	}
	
	public User getUser(String login) {
		List<User> userList = this.session.createCriteria(User.class)
							.add(Restrictions.eq("login", login))
							.list();
		if (! userList.isEmpty()) {
			return userList.get(0);	
		}
		return null;
	}
	
}

package dao;

import java.util.ArrayList;
import java.util.List;

import model.Specialist;
import model.Specialty;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class UserDao {
	private final Session session;
	
	public UserDao(Session session) {
		this.session = session;
	}
	
	public void save(User user) {
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	}
	
	public void save(User user, ArrayList<Long> specialties_ids) {
		Transaction tx = session.beginTransaction();
		ArrayList<Specialist> specialists = new ArrayList<Specialist>();
		session.save(user);
		tx.commit();
		if (specialties_ids != null) {
			for (long id : specialties_ids) {
				Specialty s;
				s = (Specialty) this.session.get(Specialty.class, id);
				Specialist specialist = new Specialist(0);
				specialist.setUser(user);
				specialist.setSpecialty(s);
				tx = session.beginTransaction();
				session.save(specialist);
				tx.commit();
				specialists.add(specialist);
			}
			user.setSpecialists(specialists);
		}
		tx = session.beginTransaction();
		session.update(user);
		tx.commit();
	}
	
	public User getUser(long userId) {
		return (User) this.session.get(User.class, userId);
	}
	
	public User getUser(String login) {
		List<User> userList = this.session.createCriteria(User.class).add(Restrictions.eq("login", login)).list();
		if (! userList.isEmpty()) {
			return userList.get(0);	
		}
		return null;
	}
	
	public User getUserByEmail(String email) {
		List<User> userList = this.session.createCriteria(User.class).add(Restrictions.eq("email", email)).list();
		if (! userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}
	
	public List<Specialty> list() {
		return this.session.createCriteria(Specialty.class).list();
	}

	public List<Specialist> getTopSpecialists() {
		return this.session.createCriteria(Specialist.class).addOrder( Order.desc("score")).setMaxResults(5).list();

	}
}

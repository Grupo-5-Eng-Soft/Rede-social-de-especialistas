package dao;

import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.QuestionStatus;
import model.Specialist;
import model.Specialty;
import model.User;

import org.hibernate.Criteria;
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
		session.save(user);
		saveSpecialties(user, specialties_ids);
		session.update(user);
		tx.commit();
	}
	
// só funciona porque um objeto altera o outro
	public void updateUser(User user) {
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
	}
	
	public void edit(User newUser, ArrayList<Long> specialties_ids) {
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, newUser.getId());
		cleanSpecialists(user);
		saveSpecialties(user, specialties_ids);
// copiando o objeto remapeado: carece de refatoração, não?
		user.setActive(newUser.isActive());
		user.setEmail(newUser.getEmail());
		user.setInstitution(newUser.getInstitution());
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		session.update(user);
		tx.commit();
	}
	
	private void saveSpecialties(User user, ArrayList<Long> specialties_ids) {
		ArrayList<Specialist> specialists = new ArrayList<Specialist>();
		if (specialties_ids != null) {
			for (long id : specialties_ids) {
				Specialty s;
				s = (Specialty) this.session.get(Specialty.class, id);
				Specialist specialist = new Specialist(0);
				specialist.setUser(user);
				specialist.setSpecialty(s);
				session.save(specialist);
				specialists.add(specialist);
			}
			user.setSpecialists(specialists);
		}
	}
	
	private void cleanSpecialists(User u) {
		for (Specialist specialist : getSpecialists(u)) {
			session.delete(specialist);
		}
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
	
	public ArrayList<Specialist> getSpecialists(User user) {
		return (ArrayList<Specialist>) this.session.createCriteria(Specialist.class)
			.add(Restrictions.eq("user", user))
			.list();
	}
	
	public List<Specialty> listSpecialty() {
		return this.session.createCriteria(Specialty.class).list();
	}

	public List<User> listUser() {
		return this.session.createCriteria(User.class).list();
	}
	
	public List<Specialist> getTopSpecialists() {
		return this.session.createCriteria(Specialist.class).addOrder( Order.desc("score")).setMaxResults(5).list();
	}

	public List<Question> getQuestionsFromSpecialties(List<Specialty> specialties) {
		Criteria questionsCriteria = this.session.createCriteria(Question.class)
			.add(Restrictions.in("specialty", specialties))
			.add(Restrictions.eq("status", QuestionStatus.OPEN));
		return questionsCriteria.list();
	}
}

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
@SuppressWarnings("unchecked")
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
	
	public void save(User user, List<Long> specialties_ids) {
		Transaction tx = session.beginTransaction();
		session.save(user);
		saveSpecialties(user, specialties_ids);
		session.update(user);
		tx.commit();
	}
	
// só funciona porque um objeto altera o outro
	public void update(User user) {
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
	}
	
	public void edit(User newUser, ArrayList<Long> specialties_ids) {
		Transaction tx = session.beginTransaction();
		User persistedUser = (User) session.load(User.class, newUser.getId());
		cleanSpecialists(persistedUser);
		saveSpecialties(persistedUser, specialties_ids);
		copyFields(persistedUser, newUser);
		session.update(persistedUser);
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
		return this.session.createCriteria(Specialist.class).addOrder(Order.desc("score")).setMaxResults(5).list();
	}

	public List<Question> getQuestionsFromSpecialties(List<Specialty> specialties) {
		Criteria questionsCriteria = this.session.createCriteria(Question.class)
			.add(Restrictions.in("specialty", specialties))
			.add(Restrictions.eq("status", QuestionStatus.OPEN));
		return questionsCriteria.list();
	}
	
	private void copyFields(User oldUser, User newUser) {
		oldUser.setActive(newUser.isActive());
		oldUser.setEmail(newUser.getEmail());
		oldUser.setInstitution(newUser.getInstitution());
		oldUser.setName(newUser.getName());
		oldUser.setPassword(newUser.getPassword());
	}
	
	private Specialist specialistWithSpecialtyFromUser(User user, long id) {
		Specialty s = (Specialty) this.session.get(Specialty.class, id);
		Specialist specialist = new Specialist(0);
		specialist.setUser(user);
		specialist.setSpecialty(s);
		return specialist;
	}
	
	private void cleanSpecialists(User u) {
		for (Specialist specialist : getSpecialists(u)) {
			session.delete(specialist);
		}
	}
	
	private void saveSpecialties(User user, List<Long> specialtiesIds) {
		List<Specialist> specialists = new ArrayList<Specialist>();
		if (specialtiesIds != null) {
			for (long specialtyId : specialtiesIds) {
				Specialist specialist = specialistWithSpecialtyFromUser(user, specialtyId);
				session.save(specialist);
				specialists.add(specialist);
			}
			user.setSpecialists(specialists);
		}
	}

}

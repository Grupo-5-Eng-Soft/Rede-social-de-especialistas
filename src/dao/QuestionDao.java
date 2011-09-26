package dao;

import java.util.ArrayList;
import java.util.Collection;

import model.Post;
import model.Question;
import model.Specialty;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class QuestionDao {
	
	private final Session session;

	public QuestionDao(Session session) {
		this.session = session;
	}

	public void save(Question question) {
		Transaction tx = session.beginTransaction();
		session.save(question);
		tx.commit();
	}
	
	public Collection<Specialty> listSpecialties() {
		SpecialtyDao dao = new SpecialtyDao(session);
		return dao.list();
	}

	public Specialty getSpecialty(Long specialtyId) {
		SpecialtyDao dao = new SpecialtyDao(session);
		return dao.getSpecialty(specialtyId);
	}

	public ArrayList<Question> listQuestions() {
		return (ArrayList<Question>) this.session.createCriteria(Question.class).list();
	}

	public Question getQuestion(Long questionId) {
		return (Question) this.session.get(Question.class, questionId);
	}

}

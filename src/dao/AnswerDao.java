package dao;

import model.Answer;
import model.Question;
import model.Specialty;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class AnswerDao {
	
	private final Session session;

	public AnswerDao(Session session) {
		this.session = session;
	}

	public void save(Answer answer) {
		Transaction tx = session.beginTransaction();
		session.save(answer);
		tx.commit();
	}
	
	public Question getQuestion(Long questionId) {
		QuestionDao dao = new QuestionDao(session);
		return dao.getQuestion(questionId);
	}
	
	public Specialty getSpecialty(Long specialtyId) {
		SpecialtyDao dao = new SpecialtyDao(session);
		return dao.getSpecialty(specialtyId);
	}
	
	public Answer getAnswer(Long answerId) {
		return (Answer) this.session.get(Answer.class, answerId);
	}

}

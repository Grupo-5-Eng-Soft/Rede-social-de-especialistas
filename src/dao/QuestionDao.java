package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Answer;
import model.Question;
import model.Specialist;
import model.Specialty;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
	
	public Answer getAnswer(Long answerId) {
		AnswerDao dao = new AnswerDao(session);
		return dao.getAnswer(answerId);
	}

	public Question getQuestion(Long questionId) {
		return (Question) this.session.get(Question.class, questionId);
	}

	public List<Specialist> getSpecialists(Specialty specialty) {
		SpecialtyDao dao = new SpecialtyDao(session);
		return dao.getSpecialists(specialty);
	}

	
	public List<Question> getLastQuestions() {
		return this.session.createCriteria(Question.class).addOrder( Order.desc("id")).setMaxResults(5).list();

	}

	public List<Question> listPublicQuestions() {
		List<Question> publicQuestions = (List<Question>) this.session.createCriteria(Question.class).
				add(Restrictions.eq("publicQuestion", true)).
				list();
		return publicQuestions;
	}

	public List<Question> listAvaiableQuestionsOf(User loggedUser) {
		Criterion avaiableQuestionsCriterion = Restrictions.disjunction().
		  add(Restrictions.eq("publicQuestion", true)).
		  add(Restrictions.in("specialty", loggedUser.getSpecialtiesOfSpecialists())).
		  add(Restrictions.eq("author", loggedUser));
		Criteria avaiableQuestionsCriteria = this.session.
				createCriteria(Question.class).
				add(avaiableQuestionsCriterion);
		return avaiableQuestionsCriteria.list();
	}
	
}

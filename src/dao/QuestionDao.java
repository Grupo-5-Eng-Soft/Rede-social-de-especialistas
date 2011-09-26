package dao;

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

}

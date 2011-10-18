package dao;

import java.util.List;

import model.QuestionMaker;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class QuestionMakerDao {

	private final Session session;
	
	public QuestionMakerDao(Session session) {
		this.session = session;
	}
	
	public void save(QuestionMaker qm) {
		Transaction tx = session.beginTransaction();
		session.save(qm);
		tx.commit();
	}
	
	public QuestionMaker getUserByEmail(String email) {
		List<QuestionMaker> qmList = this.session.createCriteria(QuestionMaker.class).add(Restrictions.eq("email", email)).list();
		if (! qmList.isEmpty()) {
			return qmList.get(0);
		}
		return null;
	}
}

package dao;

import model.Answer;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class AnswerDao {
	
	private final Session session;

	public AnswerDao(Session session) {
		this.session = session;
	}

	public void save(Answer answer) {
		
	}

}

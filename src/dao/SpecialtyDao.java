package dao;

import infra.SessionCreator;

import java.util.List;

import model.Specialty;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;


@Component
public class SpecialtyDao {
	private Session session;

	public SpecialtyDao() {
		this.session = SessionCreator.createSession();
	}

	public void save(Specialty specialty) {
		Transaction tx = session.beginTransaction();
		session.save(specialty);
		tx.commit();
	}

	public List<Specialty> list() {
		return this.session.createCriteria(Specialty.class).list();
	}
	

	
}

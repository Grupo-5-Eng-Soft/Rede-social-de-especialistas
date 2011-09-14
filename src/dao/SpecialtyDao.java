package dao;

import java.util.List;

import model.Specialty;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;


@Component
public class SpecialtyDao {
	private final Session session;

	public SpecialtyDao(Session session) {
		this.session = session;
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

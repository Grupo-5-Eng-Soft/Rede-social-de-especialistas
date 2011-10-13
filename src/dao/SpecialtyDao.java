package dao;

import java.util.ArrayList;
import java.util.List;

import model.Specialist;
import model.Specialty;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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

	public Specialty getSpecialty(Long specialtyId) {
		if(specialtyId != null) {
			return (Specialty) this.session.get(Specialty.class, specialtyId);
		}
		return null;
	}

	public ArrayList<Specialist> getSpecialists(Specialty specialty) {
		return (ArrayList<Specialist>) this.session.createCriteria(Specialist.class)
			.add(Restrictions.eq("specialty", specialty))
			.list();
	}
	
}

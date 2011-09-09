package dao;

import java.util.ArrayList;

import model.User;
import model.Specialist;
import model.Specialty;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.Component;


@Component
public class SpecialistDao {
	private Session session;

	public SpecialistDao() {
		this.session = criaSessao();
	}

	public void save(User user, ArrayList<Specialty> specialties) {
		int i;
		Transaction tx = session.beginTransaction();
		for (i = 0; i < specialties.size(); i++) {
			session.save(new Specialist(user.getId(), specialties.get(i).getSpecialty()));
		}
		tx.commit();
	}

	private Session criaSessao() {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
}

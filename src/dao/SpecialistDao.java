package dao;

import model.Specialist;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class SpecialistDao {
	
	private final Session session;

	public SpecialistDao(Session session) {
		this.session = session;
	}

	public void save(Specialist specialist) {
		
	}

}

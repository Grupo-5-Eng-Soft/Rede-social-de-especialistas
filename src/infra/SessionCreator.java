package infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class SessionCreator implements ComponentFactory<Session>{
	
	private final SessionFactory factory;
	private Session session;

	public SessionCreator(SessionFactory factory) {
		this.factory = factory;
	}
	
	@PostConstruct
	public void abre() {
		this.session = factory.openSession();
	}
	
	public Session getInstance() {
		return factory.openSession();
	}
	
	@PreDestroy
	public void fecha() {
		this.session.close();
	}
}
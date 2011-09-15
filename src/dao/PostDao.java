package dao;

import model.Post;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class PostDao {
	
	private final Session session;

	public PostDao(Session session) {
		this.session = session;
	}

	public void save(Post post) {
		
	}

}

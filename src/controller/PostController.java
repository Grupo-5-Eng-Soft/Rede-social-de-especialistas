package controller;

import model.Post;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import dao.PostDao;

@Resource
public class PostController {
	
	private final PostDao posts;
	
	public PostController(PostDao posts) {
		this.posts = posts;
	}
	
	@Path("/post/new")
	public void form() {
	}
	
	public void save(Post post) {
		posts.save(post);
	}
}

package controller;

import model.Answer;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import dao.AnswerDao;

@Resource
public class AnswerController {
	
	private final AnswerDao posts;
	
	public AnswerController(AnswerDao posts) {
		this.posts = posts;
	}
	
	@Path("/post/new")
	public void form() {
	}
	
	public void save(Answer post) {
		posts.save(post);
	}
}

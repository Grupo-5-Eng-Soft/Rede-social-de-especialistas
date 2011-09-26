package controller;

import infra.UserSession;
import interceptor.annotations.LoggedUser;
import model.Question;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.QuestionDao;

@Resource
public class QuestionController {
	private final Result result;
	private final QuestionDao dao;
	private final UserSession userSession;
	
	public QuestionController(Result result, QuestionDao dao, UserSession userSession) {
		this.result = result;
		this.dao = dao;
		this.userSession = userSession;
	}
	
	@LoggedUser
	@Path("/perguntas/nova/")
	public void form() {
		result.include("specialties", dao.listSpecialties());
	}
	
	@LoggedUser
	@Path("/perguntas/salvar/")
	public void save(Question question) {
		question.setAuthor(userSession.getLoggedUser());
		
	}

}

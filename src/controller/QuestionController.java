package controller;

import infra.UserSession;
import interceptor.annotations.LoggedUser;
import model.Question;
import model.Specialty;
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
	public void save(Question question, Long specialtyId) {
		Specialty specialty = dao.getSpecialty(specialtyId);
		question.setAuthor(userSession.getLoggedUser());
		question.setSpecialty(specialty);
		dao.save(question);
		result.redirectTo(QuestionController.class).list();
	}
	
	@Path("/perguntas/")
	public void list() {
		result.include("questions", dao.listQuestions());
	}
	
	@Path("/perguntas/{questionId}/")
	public void detail(Long questionId) {
		Question question = dao.getQuestion(questionId);
		result.include("question", question);
	}

}

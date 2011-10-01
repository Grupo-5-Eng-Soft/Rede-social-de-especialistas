package controller;

import java.util.ArrayList;

import infra.EmailSender;
import infra.UserSession;
import interceptor.annotations.LoggedUser;
import model.Question;
import model.Specialist;
import model.Specialty;
import model.User;
import model.Answer;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.QuestionDao;
import dao.AnswerDao;

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
		ArrayList<Specialist> specialists;
		Specialty specialty = dao.getSpecialty(specialtyId);
		specialists = dao.getSpecialists(specialty);
		sendEmailsToSpecialists(specialists, question);
		question.setAuthor(userSession.getLoggedUser());
		question.setSpecialty(specialty);
		dao.save(question);
		result.redirectTo(QuestionController.class).list();
	}
	
	private void sendEmailsToSpecialists(ArrayList<Specialist> specialists, Question question) {
		ArrayList<User> users = new ArrayList<User>();
		String subject = "Nova pergunta na rede social de especialistas - " + question.getTitle();
		String message = question.getDescription();
		for (Specialist specialist : specialists)
			users.add(specialist.getUser());
		Thread thread = new Thread(new EmailSender(users, message, subject));
		thread.start();
	}
	
	@Path("/perguntas/")
	public void list() {
		result.include("questions", dao.listQuestions());
	}
	
	@Path("/perguntas/{questionId}/")
	public void detail(Long questionId) {
		Question question = dao.getQuestion(questionId);
		result.include("question", question, "answer", question.getPosts());
	}

}

package controller;

import java.util.ArrayList;

import infra.EmailSender;
import infra.UserSession;
import interceptor.annotations.LoggedUser;
import model.Answer;
import model.Question;
import model.Specialist;
import model.Specialty;
import model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.AnswerDao;

@Resource
public class AnswerController {
	
	private final Result result;
	private final AnswerDao dao;
	private final UserSession userSession;
	
	public AnswerController(Result result, AnswerDao dao, UserSession userSession) {
		this.result = result;
		this.dao = dao;
		this.userSession = userSession;
	}
	
	@LoggedUser
	@Path("/perguntas/responder/")
	public void answer(Answer answer, Long questionId) {
		Question question = dao.getQuestion(questionId);
		answer.setAuthor(userSession.getLoggedUser());
		answer.setSpecialty(question.getSpecialty());
		answer.setQuestion(question);
		sendEmailToAuthor(question, answer);
		dao.save(answer);
		result.redirectTo(QuestionController.class).list();
	}
	
	private void sendEmailToAuthor(Question question, Answer answer) {
		ArrayList<User> users = new ArrayList<User>();
		String subject = "Uma resposta para sua pergunta - " + question.getTitle();
		String message = answer.getDescription();
		users.add(question.getAuthor());
		Thread thread = new Thread(new EmailSender(users, message, subject));
		thread.start();
	}
	
	public void save(Answer answer) {
		dao.save(answer);
	}
}

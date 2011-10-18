package controller;

import java.util.ArrayList;

import infra.EmailSender;
import infra.UserSession;
import model.Answer;
import model.Question;
import model.Specialty;
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
	
	@Path("/perguntas/{questionId}/responder/")
	public void answer(Answer answer, Long questionId) {
		Question question = dao.getQuestion(questionId);
		Specialty specialty = question.getSpecialty();
		if (!userSession.isSpecialistIn(specialty)) {
			result.redirectTo(ErrorController.class).errorscreen();
			return;
		}
		answer.setAuthor(userSession.getLoggedUser());
		answer.setQuestion(question);
		sendEmailToAuthor(question, answer);
		dao.save(answer);
		result.redirectTo(QuestionController.class).detail(questionId);
	}
	
	private void sendEmailToAuthor(Question question, Answer answer) {
		ArrayList<String> receivers = new ArrayList<String>();
		String subject = "Uma resposta para sua pergunta - " + question.getTitle();
		String message = answer.getDescription();
		receivers.add(question.getAuthor().getEmail());
		Thread thread = new Thread(new EmailSender(receivers, message, subject));
		thread.start();
	}
	
	public void save(Answer answer) {
		dao.save(answer);
	}
}

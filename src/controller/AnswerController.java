package controller;

import infra.EmailSender;
import infra.UserSession;

import java.util.ArrayList;

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
		sendEmailToAuthor(answer);
		dao.save(answer);
		result.redirectTo(QuestionController.class).detail(questionId);
	}
	
	private void sendEmailToAuthor(Answer answer) {
		ArrayList<String> receivers = new ArrayList<String>();
		Question question = answer.getQuestion();
		String subject = "Uma resposta para sua pergunta - " + question.getTitle();
		String message = answer.getDescription();
		receivers.add(question.getEmail());
		Thread thread = new Thread(new EmailSender(receivers, message, subject));
		thread.start();
	}
}

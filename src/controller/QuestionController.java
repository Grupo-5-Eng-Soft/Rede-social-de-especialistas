package controller;

import infra.Email;
import infra.EmailSender;
import infra.UserSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Question;
import model.Specialist;
import model.Specialty;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import dao.QuestionDao;

@Resource
public class QuestionController {
	private final Result result;
	private final QuestionDao dao;
	private final UserSession userSession;
	private final Validator validator;
	
	public QuestionController(Result result, QuestionDao dao, UserSession userSession, Validator validator) {
		this.result = result;
		this.dao = dao;
		this.userSession = userSession;
		this.validator = validator;
	}
	
	
	@Path("/perguntas/nova/{specialtyId}")
	public void form(Long specialtyId) {
		result.include("specialties", dao.listSpecialties());
		result.include("questionId", specialtyId);
	}

	@Path("/perguntas/nova/")
	public void form() {
		result.include("specialties", dao.listSpecialties());
	}
	
	@Path("/perguntas/salvar/")
	public void save(Question question, Long specialtyId) {
		validate(question, specialtyId);
		
		question.setData(new Date());
		
		Specialty specialty = dao.getSpecialty(specialtyId);
		List<Specialist> specialists = dao.getSpecialists(specialty);
		
		if(userSession.isAuthenticated()) {
			question.setEmail(null);
			question.setAuthor(userSession.getLoggedUser());
		}
		else {
			question.setAuthor(null);
		}
		question.setSpecialty(specialty);
		dao.save(question);
		sendEmailsToSpecialists(specialists, question);
		result.redirectTo(QuestionController.class).list();
	}

	private void sendEmailsToSpecialists(List<Specialist> specialists, Question question) {
		ArrayList<String> receivers = new ArrayList<String>();
		String subject = "Nova pergunta na rede social de especialistas - " + question.getTitle();
		
		String message = Email.templateForMessage(question.getDescription(), question.getId());
	
		for (Specialist specialist : specialists)
			receivers.add(specialist.getUser().getEmail());
		Thread thread = new Thread(new EmailSender(receivers, message, subject));
		thread.start();
	}
	
	@Path("/perguntas/")
	public void list() {
		if (!userSession.isAuthenticated())
			result.include("questions", dao.listPublicQuestions());
		else
			result.include("questions", dao.listAvaiableQuestionsOf(userSession.getLoggedUser()));
	}
	
	@Path("/perguntas/{questionId}/")
	public void detail(Long questionId) {
		Question question = dao.getQuestion(questionId);
		result.include("isSpecialist", userSession.isSpecialistIn(question.getSpecialty()));
		result.include("question", question);
		result.include("answer", question.getAnswers());
	}

	private void validate(final Question question, final Long specialtyId) {
		validator.checking(new Validations() {{
			that(dao.getSpecialty(specialtyId) != null, "specialty", "pergunta.deve.pertencer.a.uma.especialidade");
			that(!question.getTitle().isEmpty(), "question.title", "titulo.pergunta.nao.pode.ser.vazio");
			that(!question.getDescription().isEmpty(), "question.description", "conteudo.pergunta.nao.pode.ser.vazio");
		}});
		
		validator.onErrorRedirectTo(this).form(specialtyId);
	}
}

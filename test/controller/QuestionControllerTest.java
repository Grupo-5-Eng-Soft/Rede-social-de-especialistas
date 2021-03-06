package controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.HashMap;

import infra.UserSession;
import infra.EmailSender;
import model.Answer;
import model.AnswerClassification;
import model.Question;
import model.QuestionStatus;
import model.Specialty;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sun.security.krb5.Asn1Exception;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import dao.QuestionDao;

public class QuestionControllerTest {
	
	private @Mock QuestionDao dao;
	private MockResult result = new MockResult();
	private QuestionController controller;
	private UserSession session = new UserSession();
	private Validator validator = new MockValidator();
	private @Mock EmailSender emailSender;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new QuestionController(result, dao, session, validator, emailSender);
	}

	@Test
	public void shouldSaveQuestion() throws Exception {
		Specialty especialidade = new Specialty();
		when(dao.getSpecialty(1L)).thenReturn(especialidade);
		Question q = new Question();
		q.setTitle("Titulo da pergunta");
		q.setDescription("Conteudo da pergunta");
		controller.save(q, 1L);
		verify(dao).save(q);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldNotSaveQuestionWithEmptyTitle() throws Exception {
		Question q = new Question();
		q.setTitle("");
		q.setDescription("Conteudo da pergunta");
		controller.save(q, 1L);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldNotSaveQuestionWithEmptyDescription() throws Exception {
		Question q = new Question();
		q.setTitle("Titulo da pergunta");
		q.setDescription("");
		controller.save(q, 1L);
	}
	
	@Test
	public void shouldShowQuestionDetails() {
		Question question = createQuestionWithSpecialtyAndAuthor();
		when(dao.getQuestion(question.getId())).thenReturn(question);
		controller.detail(question.getId());
		Question includedQuestion = result.included("question");
		assertNotNull(includedQuestion);
	}
	
	@Test
	public void shouldFinalizeQuestion() {
		Question question = createQuestionWithSpecialtyAndAuthor();
		session.login(question.getAuthor());
		Answer answer = createAnswerWithAuthor(question);
		User author = answer.getAuthor();
		author.addSpecialty(question.getSpecialty());
		when(dao.getAnswer(answer.getId())).thenReturn(answer);
		controller.finalizeQuestion(answer.getId(), 3);
		assertEquals(QuestionStatus.FINALIZED, question.getStatus());
	}
	
	@Test
	public void shouldNotFinalizeQuestionWhenNotAuthor() {
		Question question = createQuestionWithSpecialtyAndAuthor();
		Answer answer = createAnswerWithAuthor(question);
		User loggedUser = new User();
		loggedUser.setId(question.getId()+100L);
		session.login(loggedUser);
		when(dao.getAnswer(answer.getId())).thenReturn(answer);
		controller.finalizeQuestion(answer.getId(), 3);
		assertNotSame(QuestionStatus.FINALIZED, question.getStatus());
	} 

	private Answer createAnswerWithAuthor(Question question) {
		User author = new User();
		author.setId(1L);
		author.setEmail("lala@gmail.com");
		author.setName("nome");
		Answer answer = new Answer();
		answer.setDescription("resposta");
		answer.setId(1L);
		answer.setQuestion(question);
		answer.setAuthor(author);
		return answer;
	}

	private Question createQuestionWithSpecialtyAndAuthor() {
		User author = new User();
		author.setId(666L);
		author.setEmail("lala2@gmail.com");
		author.setName("questionauthor");
		Specialty specialty = new Specialty();
		specialty.setId(1L);
		specialty.setName("spec");
		Question question = new Question();
		question.setId(0);
		question.setTitle("Duvida");
		question.setDescription("Descricao");
		question.setSpecialty(specialty);
		question.setAuthor(author);
		return question;
	}
}

package controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.HashMap;

import infra.UserSession;
import infra.EmailSender;
import model.Question;
import model.Specialty;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
		Question question = createQuestion();
		when(dao.getQuestion(question.getId())).thenReturn(question);
		controller.detail(question.getId());
		Question includedQuestion = result.included("question");
		assertNotNull(includedQuestion);
	}

	private Question createQuestion() {
		Question question = new Question();
		question.setId(0);
		question.setTitle("Duvida");
		question.setDescription("Descricao");
		return question;
	}
}

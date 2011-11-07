package controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import infra.UserSession;
import model.Answer;
import model.Question;
import model.Specialty;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import dao.AnswerDao;

public class AnswerControllerTest {
	
	private @Mock AnswerDao dao;
	private Result result = new MockResult();
	private AnswerController controller;
	private @Mock UserSession session;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new AnswerController(result, dao, session);
	}
	
	@Test
	public void shouldSendEmailForAuthorAndSaveAnswer() throws Exception {
		User user = mock(User.class);
		Specialty vraptor = mock(Specialty.class);
		
		Question question = aQuestionWithSpecialty(vraptor);
		Answer answer = anAnswerToQuestion(question);
		
		when(session.getLoggedUser()).thenReturn(user);
		when(session.isAuthenticated()).thenReturn(true);
		when(session.isSpecialistIn(vraptor)).thenReturn(true);
		
		when(dao.getQuestion(1l)).thenReturn(question);
		
		controller.answer(answer, 1l);
		verify(dao).save(answer);
	}
	
	private Question aQuestionWithSpecialty(Specialty specialty) {
		Question question = new Question();
		question.setTitle("Titulo da pergunta.");
		question.setSpecialty(specialty);
		question.setEmail("email@abc.x");
		return question;
	}
	
	private Answer anAnswerToQuestion(Question question) {
		Answer answer = new Answer();
		answer.setQuestion(question);
		return answer;
	}

}

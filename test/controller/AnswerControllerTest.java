package controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import infra.EmailSender;
import infra.UserSession;
import model.Answer;
import model.Question;
import model.Role;
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
	private @Mock EmailSender emailSender;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new AnswerController(result, dao, session, emailSender);
	}
	
	@Test
	public void shouldSendEmailForAuthorAndSaveAnswer() throws Exception {
		User user = mock(User.class);
		Specialty vraptor = mock(Specialty.class);
		
		User author = createAuthor();
		Question question = aQuestionWithSpecialty(author, vraptor);
		Answer answer = anAnswerToQuestion(question);
		
		when(session.getLoggedUser()).thenReturn(user);
		when(session.isAuthenticated()).thenReturn(true);
		when(session.isSpecialistIn(vraptor)).thenReturn(true);
		
		when(dao.getQuestion(1l)).thenReturn(question);
		
		controller.answer(answer, 1l);
		verify(dao).save(answer);
	}
	
	private User createAuthor() {
		User user = new User();
		
		user.setId(1);
		user.setEmail("chico@email.com");
		user.setLogin("chico");
		user.setSpecialists(null);
		user.setActive(true);
		user.setRole(Role.USER);
		
		return user;
	}

	private Question aQuestionWithSpecialty(User author, Specialty specialty) {
		Question question = new Question();
		question.setTitle("Titulo da pergunta.");
		question.setSpecialty(specialty);
		question.setEmail("email@abc.x");
		question.setAuthor(author);
		return question;
	}
	
	private Answer anAnswerToQuestion(Question question) {
		Answer answer = new Answer();
		answer.setQuestion(question);
		return answer;
	}

}

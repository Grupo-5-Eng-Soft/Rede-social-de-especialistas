package dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import model.Question;
import model.Specialty;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class QuestionDaoTest {
	
	private QuestionDao dao;
	private @Mock Session session;
	private @Mock Transaction tx;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(session.beginTransaction()).thenReturn(tx);
		dao = new QuestionDao(session);
	}
	
	@Test
	public void shouldSaveQuestion() {
		Question question = createQuestionWithSpecialtyAndAuthor();
		dao.save(question);
		verify(session).save(question);
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

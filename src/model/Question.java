package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Valid;
import java.util.HashMap;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User author;
	
	private String email;
	
	@OneToMany(mappedBy="question")
	private Collection<Answer> answers = new ArrayList<Answer>();
	
	@ManyToOne
	private Specialty specialty;

	@Valid
	@NotNull
	private String title;
	
	@NotNull
	private Date creationDate;
	
	@Valid
	@NotNull
	private String description;
	
	@Valid
	@NotNull
	private boolean publicQuestion = true;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private QuestionStatus status = QuestionStatus.OPEN;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Collection<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answer) {
		this.answers = answer;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.getAuthor() == null ? this.email : this.getAuthor().getEmail();
	}

	public boolean isPublicQuestion() {
		return publicQuestion;
	}

	public void setPublicQuestion(boolean publicQuestion) {
		this.publicQuestion = publicQuestion;
	}

	public Date getData() {
		return creationDate;
	}

	public void setData(Date data) {
		this.creationDate = data;
	}
	
	public HashMap<String, Boolean> getAnswersAuthorsQualification() {
		User author;
		HashMap<String, Boolean> answersAuthors = new HashMap<String, Boolean>();
		for (Answer answer : this.answers) {
			author = answer.getAuthor();
			answersAuthors.put(author.getLogin(), author.isSpecialistIn(this.specialty));
			answersAuthors.get(author.getLogin());
		}
		return answersAuthors;
	}

	public QuestionStatus getStatus() {
		return status;
	}

	public void setStatus(QuestionStatus status) {
		this.status = status;
	}
	
	@Transient
	public boolean isFinalized() {
		return status == QuestionStatus.FINALIZED;
	}
	
	@Transient
	public boolean isAnswered() {
		return status == QuestionStatus.ANSWERED;
	}
	
	@Transient
	public boolean isOpen() {
		return status == QuestionStatus.OPEN;
	}
}

package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.Valid;

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
	
	@Valid
	@NotNull
	private String description;
	
	@Valid
	@NotNull
	private boolean publicQuestion = true;

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
	
}

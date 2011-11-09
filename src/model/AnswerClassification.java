package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.NotNull;


@Entity
public class AnswerClassification {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	private Answer answer;
	
	@NotNull
	private int score;
	
	//eh obrigado a ter por conta do hibernate
	public AnswerClassification() {
	}
	
	public AnswerClassification(Answer answer, Integer score) {
		answer.setClassification(this);
		User author = answer.getAuthor();
		//TODO: o que fazer se o autor da resposta nao for especialista?
		Specialist specialistAt = author.getSpecialistAt(answer.getQuestion().getSpecialty());
		if (specialistAt != null) {
			specialistAt.addScore(score);
		}
		this.answer = answer;
		this.score = score;
	}

	public Answer getAnswer() {
		return answer;
	}
}

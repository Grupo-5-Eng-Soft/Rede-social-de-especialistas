package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class Specialist implements Serializable{
	
	private int score;
	
	@Id
	@ManyToOne
	private User user;
	
	@Id
	@ManyToOne
	private Specialty specialty;
	
	public Specialist() {
		super();
	}
	
	public Specialist(int score) {
		this.score = score;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}

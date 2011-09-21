package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class Specialist implements Serializable{
	
	
	@NotNull
	@NotEmpty
	private Long score;
	
	@Id
	@NotNull
	@NotEmpty
	@ManyToOne
	private User user;
	
	@Id
	@NotNull
	@NotEmpty
	@ManyToOne
	private Specialty specialty;
	
	public Specialist(Long score) {
		this.score = score;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
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
/*@Embeddable
public class Specialist {

	// Lets suppose you have added this field
	@Column
	private int score;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPECIALTY_ID")
	private Specialty specialty;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public boolean equals(Object instance) {
		if (instance == null)
			return false;

		if (!(instance instanceof Specialist))
			return false;

		Specialist other = (Specialist) instance;
		if (!(user.getId() == other.user.getId()))
			return false;

		if (!(specialty.getId() == other.specialty.getId()))
			return false;

		if (!(score == other.score))
			return false;

		return true;
	}
	
	
}
*/
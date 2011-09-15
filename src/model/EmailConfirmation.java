package model;

import hash.HashCalculator;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.NotEmpty;

@Entity
public class EmailConfirmation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	private User user;

	@NotEmpty
	private String confirmationString;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		HashCalculator hash = new HashCalculator(user.getLogin() + user.getEmail());
		this.user = user;
		this.confirmationString = hash.getValue();
	}

	public String getConfirmationString() {
		return confirmationString;
	}
}

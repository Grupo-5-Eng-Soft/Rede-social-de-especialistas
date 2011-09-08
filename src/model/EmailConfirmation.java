package model;

import hash.HashCalculator;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.validator.NotEmpty;

@Entity
public class EmailConfirmation implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "user_pk")
	private User user;

	@NotEmpty
	private String confirmationString;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.confirmationString = HashCalculator.calculateHash(user.getLogin());
	}

	public String getConfirmationString() {
		return confirmationString;
	}

}

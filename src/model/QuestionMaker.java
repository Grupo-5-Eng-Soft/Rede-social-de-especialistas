package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import org.hibernate.validator.NotEmpty;


@Entity
public class QuestionMaker {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

}

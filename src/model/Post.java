package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	private String title;
	
	@NotNull
	@NotEmpty
	private String content;
	
	@ManyToOne
	private Question question;
	
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

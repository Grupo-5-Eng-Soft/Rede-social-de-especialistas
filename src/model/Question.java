package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User author;
	
	@OneToMany(mappedBy="question")
	private Collection<Post> posts = new ArrayList<Post>();
	
	@ManyToOne
	private Specialty specialty;
	
	private String title;
	
	private String description;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Collection<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
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

	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}
	
}

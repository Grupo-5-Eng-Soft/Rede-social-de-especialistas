package model;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.NotEmpty;

@Entity
public class Specialty {
	
	long id;
	
	@NotEmpty
	private String name;
	
	private Collection<User> users;
	
	@ManyToMany(
	        mappedBy = "specialties",
	        targetEntity = User.class
	)
	public Collection<User> getUsers() {
		return users;
	}
	
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name="specialty_id")
	public long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}

}

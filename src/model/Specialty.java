package model;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotEmpty;

@Entity
public class Specialty {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String name;
	
	@OneToMany(mappedBy="specialty")
	private Collection<Question> question;
	
	@OneToMany(mappedBy="specialty")
	private Collection<Specialist> specialists;
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public boolean equals(Specialty s) {
		return this.id == s.getId();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}

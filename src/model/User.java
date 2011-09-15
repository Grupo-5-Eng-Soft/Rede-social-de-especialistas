package model;

import hash.HashCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
	private Collection<Specialty> specialties = new ArrayList<Specialty>();
	
	@Column(unique=true)
	@NotEmpty
	private String login;
	
	private String password;
	
	@NotEmpty
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts = new ArrayList<Post>();
	
	private boolean active;
	
	public void setSpecialties(Collection<Specialty> specialties) {
		this.specialties = specialties;
	}
	
	public Collection<Specialty> getSpecialties() {
		return specialties;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty
	public String getPassword() {
		return this.password;
	}
	
	public void setPasswordFromRawString(String password) {
		HashCalculator encryption = new HashCalculator(password);
		this.password = encryption.getValue();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Transient
	public boolean isAdmin() {
		return role == Role.ADMIN;
	}
}


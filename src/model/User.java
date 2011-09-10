package model;

import java.util.Collection;

import hash.HashCalculator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
public class User {
	
	private long id;
	
	private Collection<Specialty> specialties;
	
	@Column(unique=true)
	@NotEmpty
	private String login;
	
	private String password;
	
	@NotEmpty
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean active;
	
	public void setSpecialties(Collection<Specialty> specialties) {
		this.specialties = specialties;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_specialties",
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "specialty_id") }
	)
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

	@Id
	@GeneratedValue
	@Column(name="user_id")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public static void main(String args[]) {
		User u = new User();
		u.setPassword("1234");
		System.out.println(u.getPassword());
		u.setPassword("1234");
		System.out.println(u.getPassword());
	}
}


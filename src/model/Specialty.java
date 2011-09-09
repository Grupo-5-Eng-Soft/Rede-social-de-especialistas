package model;


import javax.persistence.Entity;

@Entity
public class Specialty {
	
	private String specialty;
	
	
	public String getSpecialty(){
		return specialty;
	}

}

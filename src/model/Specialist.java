package model;


import javax.persistence.Entity;
import javax.persistence.Id;


@SuppressWarnings("unused")
@Entity
public class Specialist {

	@Id
	private long user_id;	

	@Id
	private long specialty_id;
	
	
	
	public Specialist(long user_id, long specialty_id){
		this.user_id = user_id;
		this.specialty_id = specialty_id;
	}
	
	
}

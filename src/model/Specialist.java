package model;


import javax.persistence.Entity;
import javax.persistence.Id;


@SuppressWarnings("unused")
@Entity
public class Specialist {

	@Id
	private long id;	
	@Id
	private String especialidade;
	
	public Specialist(long id, String especialidade){
		this.id = id;
		this.especialidade = especialidade;
	}
	
	
}

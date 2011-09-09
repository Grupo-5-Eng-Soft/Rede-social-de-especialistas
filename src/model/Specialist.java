package model;


import javax.persistence.Entity;
import javax.persistence.Id;


@SuppressWarnings("unused")
@Entity
public class Specialist {

	@Id
	private int id;	
	@Id
	private String especialidade;
	
	public Specialist(int id,String especialidade){
		this.id = id;
		this.especialidade = especialidade;
	}
	
	
}

package infra;

import java.io.Serializable;

import model.Specialist;
import model.Specialty;
import model.User;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class UserSession implements Serializable {
	
	private User loggedUser;
	
	public void login(User user) {
		this.loggedUser = user;
	}
	
	public void logout() {
		this.loggedUser = null;
	}
	
	public boolean isAuthenticated(){
		return this.loggedUser != null;
	}
	
	public boolean isAdmin(){
		if (this.loggedUser != null)
			return this.loggedUser.isAdmin();
		return false;
	}
	
	public User getLoggedUser(){
		return this.loggedUser;
	}

	public boolean isSpecialistIn(Specialty specialty) {
		if (loggedUser == null)
			return false;
		for (Specialist specialist : loggedUser.getSpecialists()) {
			if (specialist.getSpecialty().equals(specialty))
				return true;
		}
		return false;
	}
}

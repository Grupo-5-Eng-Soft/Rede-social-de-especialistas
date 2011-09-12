package infra;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import model.User;

@Component
@SessionScoped
public class UserSession {
	
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
	
	public User getLoggedUser(){
		return this.loggedUser;
	}
}

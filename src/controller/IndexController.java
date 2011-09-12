package controller;

import infra.UserSession;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {
	private final UserSession userSession;
	
	public IndexController(UserSession userSession) {
		this.userSession = userSession;
	}
	
	@Path("/")
	public void index() {}
}

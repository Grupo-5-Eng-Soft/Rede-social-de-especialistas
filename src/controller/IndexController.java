package controller;

import infra.UserSession;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {
	
	private UserSession session;

	public IndexController(UserSession session) {
		this.session = session;
	}
	
	@Path("/")
	public void index() {}
}

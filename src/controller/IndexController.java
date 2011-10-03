package controller;

import dao.UserDao;
import infra.UserSession;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {
	
	private UserSession session;
	private final Result result;
	private final UserDao userdao;
	

	public IndexController(Result result,UserSession session,UserDao userdao) {
		this.result = result;
		this.session = session;
		this.userdao = userdao;
	}
	
	@Path("/")
	public void index() {
		result.include("specialist", userdao.getTopSpecialists());

	}
}

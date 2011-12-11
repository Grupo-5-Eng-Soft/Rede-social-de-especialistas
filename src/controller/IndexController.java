package controller;

import dao.QuestionDao;
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
	private final QuestionDao questiondao;

	public IndexController(Result result,UserSession session,UserDao userdao,QuestionDao questiondao) {
		this.result = result;
		this.session = session;
		this.userdao = userdao;
		this.questiondao = questiondao;
	}
	
	@Path("/")
	public void index() {
		result.include("specialists", userdao.getTopSpecialists());
		result.include("questions", questiondao.getLastQuestions(session.getLoggedUser()));
	}
	
	@Path("/sobre/")
	public void about() {
	}
}

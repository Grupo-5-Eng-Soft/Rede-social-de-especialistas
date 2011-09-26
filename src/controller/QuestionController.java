package controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.QuestionDao;

@Resource
public class QuestionController {
	private final Result result;
	private final QuestionDao dao;
	
	public QuestionController(Result result, QuestionDao dao) {
		this.result = result;
		this.dao = dao;
	}
	
	@Path("/perguntas/nova/")
	public void form() {
		result.include("specialties", dao.listSpecialties());
	}

}

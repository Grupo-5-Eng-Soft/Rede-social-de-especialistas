package controller;

import model.Specialty;
import dao.SpecialtyDao;
import dao.UserDao;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class SpecialtyController {
	private final Result result;
	private final SpecialtyDao dao;
	private final UserSession userSession;
	
	public SpecialtyController(Result result, SpecialtyDao dao, UserSession userSession) {
		this.userSession = userSession;
		this.result = result;
		this.dao = dao;
	}
	
	@Path("/especialidades/nova/")
	public void specialtyForm() { }
	
	@Path("/especialidades/salvar/")
	public void save(Specialty specialty) {
		dao.save(specialty);
		result.redirectTo(SpecialtyController.class).list();
	}
	
	@Path("/especialidades/listar/")
	public void list() {
		result.include("specialties", dao.list());
	}
}

package controller;

import interceptor.annotations.Admin;
import model.Specialty;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.SpecialtyDao;

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
	
	@Admin
	@Path("/especialidades/nova/")
	public void specialtyForm() { }
	
	@Admin
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

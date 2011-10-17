package controller;

import infra.UserSession;
import interceptor.annotations.Admin;
import model.Specialty;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import dao.SpecialtyDao;

@Resource
public class SpecialtyController {
	private final Result result;
	private final SpecialtyDao dao;
	private final Validator validator;
	private final UserSession userSession;
	
	public SpecialtyController(Result result, SpecialtyDao dao, UserSession userSession, Validator validator) {
		this.result = result;
		this.dao = dao;
		this.validator = validator;
		this.userSession = userSession;
	}
	
	@Admin
	@Path("/especialidades/nova/")
	public void specialtyForm() { }
	
	@Admin
	@Path("/especialidades/salvar/")
	public void save(Specialty specialty) {
		validate(specialty);
		dao.save(specialty);
		result.redirectTo(SpecialtyController.class).list();	
	}
	
	private void validate(final Specialty specialty) {
		validator.checking(new Validations() {{
			that(!specialty.getName().isEmpty(), "specialty.name", "nome.especialidade.nao.vazio");
			that(dao.getSpecialtyByName(specialty.getName()) == null, "specialty.name", "especialidade.existente.no.sistema");
		}});
		
		validator.onErrorRedirectTo(this).specialtyForm();
	}

	@Path("/especialidades/listar/")
	public void list() {
		result.include("specialties", dao.list());
	}
	
	@Path("/especialidades/{specialtyId}/")
	public void detail(long specialtyId) {
		Specialty specialty = dao.getSpecialty(specialtyId);
		result.include("isAdmin", userSession.isAdmin());
		result.include("specialty", specialty);
		result.include("specialists", dao.getSpecialists(specialty));
	}
	
	@Admin
	@Path("/especialidades/{specialtyId}/atualizar/")
	public void update(long specialtyId, Specialty specialty) {
		specialty.setId(specialtyId);
		dao.saveOrUpdate(specialty);
		result.redirectTo(SpecialtyController.class).detail(specialtyId);	
	}
	
	@Admin
	@Path("/especialidades/{specialtyId}/editar/")
	public void edit(long specialtyId) {
		result.include("specialty", dao.getSpecialty(specialtyId));
	}
}

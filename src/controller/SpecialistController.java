package controller;

import model.Specialist;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import dao.SpecialistDao;

@Resource
public class SpecialistController {
	
	private final SpecialistDao specialists;
	
	public SpecialistController(SpecialistDao specialists) {
		this.specialists = specialists;
	}
	
	@Path("/specialist/")
	public void form() {
	}
	
	public void save(Specialist specialist) {
		specialists.save(specialist);
	}
}

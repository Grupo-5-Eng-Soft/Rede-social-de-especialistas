package controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import infra.UserSession;
import model.Specialty;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import dao.SpecialtyDao;

public class SpecialtyControllerTest {
	
	private @Mock SpecialtyDao dao;
	private MockResult result = new MockResult();
	private SpecialtyController controller;
	private UserSession session = new UserSession();
	private Validator validator = new MockValidator();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new SpecialtyController(result, dao, session, validator);
	}

	@Test
	public void shouldSaveSpecialty() throws Exception {
		Specialty s = validSpecialty();
		controller.save(s);
		verify(dao).save(s);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldNotSaveSpecialtyWithSameName() throws Exception {
		Specialty vraptor = validSpecialty();
		when(dao.getSpecialtyByName("vraptor")).thenReturn(vraptor);
		
		controller.save(vraptor);
	}

	private Specialty validSpecialty() {
		Specialty vraptor = new Specialty();
		vraptor.setName("vraptor");
		vraptor.setId(1L);
		return vraptor;
	}
	
	@Test
	public void shouldDetailSpecialty() {
		Specialty spec = validSpecialty();
		when(dao.getSpecialty(spec.getId())).thenReturn(spec);
		controller.detail(spec.getId());
		result.included("specialty");
	}
	
	@Test
	public void shouldUpdateSpecialty() {
		Specialty updatedSpec = validSpecialty();
		controller.update(updatedSpec.getId(), updatedSpec);
		verify(dao).saveOrUpdate(updatedSpec);
	}
	
	@Test
	public void shouldDeleteSpecialty() {
		Specialty spec = validSpecialty();
		controller.delete(spec.getId());
		verify(dao).delete(spec.getId());
	}
	
	@Test
	public void shouldEditSpecialty() {
		Specialty spec = validSpecialty();
		controller.edit(spec.getId());
		result.included("specialty");
	}
	
	@Test
	public void shouldListSpecialties() {
		controller.list();
		result.included("specialties");
	}

}

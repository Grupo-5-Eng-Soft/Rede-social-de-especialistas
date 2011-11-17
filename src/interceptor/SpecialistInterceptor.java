package interceptor;

import controller.IndexController;
import controller.UserController;
import infra.UserSession;
import interceptor.annotations.LoggedUser;
import interceptor.annotations.Specialist;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class SpecialistInterceptor implements Interceptor {

	private final Result result;
	private final UserSession userSession;
	
	public SpecialistInterceptor(UserSession userSession, Result result) {
		this.userSession = userSession;
		this.result = result;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Specialist.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if (userSession.getLoggedUser().isSpecialist())
			stack.next(method, resourceInstance);
		else
			result.redirectTo(UserController.class).notSpecialistInitialPage();
	}

}

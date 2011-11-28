package interceptor;

import infra.UserSession;
import interceptor.annotations.LoggedUser;
import interceptor.annotations.NotSpecialist;
import interceptor.annotations.Specialist;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import controller.IndexController;
import controller.UserController;

@Intercepts
public class NotSpecialistInterceptor implements Interceptor {

	private final Result result;
	private final UserSession userSession;
	
	public NotSpecialistInterceptor(UserSession userSession, Result result) {
		this.userSession = userSession;
		this.result = result;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		boolean b = method.containsAnnotation(NotSpecialist.class) || method.getResource().getType().isAnnotationPresent(NotSpecialist.class);
		return method.containsAnnotation(NotSpecialist.class)
				|| method.getResource().getType().isAnnotationPresent(NotSpecialist.class);
		
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if (!userSession.getLoggedUser().isSpecialist()) {
			stack.next(method, resourceInstance);
		}
		else {
			result.redirectTo(UserController.class).specialistInitialPage();
		}
	}

}

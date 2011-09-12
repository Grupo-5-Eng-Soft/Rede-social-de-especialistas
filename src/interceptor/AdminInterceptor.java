package interceptor;

import static br.com.caelum.vraptor.view.Results.http;
import infra.UserSession;
import interceptor.annotations.Admin;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Lazy
@Intercepts
public class AdminInterceptor implements Interceptor {
	
	private final Result result;
	private final UserSession session;

	public AdminInterceptor(UserSession session, Result result) {
		this.session = session;
		this.result = result;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Admin.class) 
			|| method.getResource().getType().isAnnotationPresent(Admin.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		if(hasLoggedUser() && session.getLoggedUser().isAdmin()) {
			stack.next(method, instance);
		} else {
			result.use(http()).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}

	private boolean hasLoggedUser() {
		return session.getLoggedUser() != null;
	}
}

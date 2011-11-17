package interceptor;

import static br.com.caelum.vraptor.view.Results.http;
import infra.UserSession;
import interceptor.annotations.LoggedUser;

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
public class LoggedUserInterceptor implements Interceptor {
	
	private final Result result;
	private final UserSession session;

	public LoggedUserInterceptor(UserSession session, Result result) {
		this.session = session;
		this.result = result;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(LoggedUser.class) 
			|| method.getResource().getType().isAnnotationPresent(LoggedUser.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		if (session.isAuthenticated()) {
			stack.next(method, instance);
		} else {
			result.use(http()).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}

}

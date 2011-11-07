package interceptor;

import static br.com.caelum.vraptor.view.Results.http;
import infra.UserSession;
import interceptor.annotations.ModifiesUser;

import javax.servlet.http.HttpServletResponse;

import model.User;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import dao.UserDao;

@Lazy
@Intercepts(after=ParametersInstantiatorInterceptor.class)
public class UserInterceptor implements Interceptor{
	
	private final Result result;
	private final UserSession session;
	private final UserDao users;
	private final MethodInfo info;

	public UserInterceptor(UserSession session, UserDao users, Result result, MethodInfo info) {
		this.session = session;
		this.users = users;
		this.result = result;
		this.info = info;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(ModifiesUser.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		Long userIdViaURL = (Long) info.getParameters()[0];
		User daoUser = users.getUser(userIdViaURL);
		User loggedUser = session.getLoggedUser();
		
		if(daoUser == null) {
			result.notFound();
			return;
		}
		if(loggedUser.getId() == daoUser.getId()) {
			stack.next(method, instance);
		} else {
			result.use(http()).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}
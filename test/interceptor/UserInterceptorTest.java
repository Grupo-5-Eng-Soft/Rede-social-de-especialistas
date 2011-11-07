package interceptor;

import static br.com.caelum.vraptor.view.Results.http;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import infra.UserSession;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.DefaultMethodInfo;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.HttpResult;
import dao.UserDao;

public class UserInterceptorTest {
	
	private static final long OTHER_ID = 12323L;
	private final MethodInfo info = new DefaultMethodInfo();
	private @Mock Result result;

	private @Mock UserDao users;
	private @Mock InterceptorStack stack;
	private @Mock ResourceMethod method;
	private @Mock HttpResult httpResult;
	private @Mock UserSession session;

	private UserInterceptor interceptor;
	private User user;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		interceptor = new UserInterceptor(session, users, result, info);

		user = new User();
		user.setId(123L);

		when(users.getUser(user.getId())).thenReturn(user);
		when(result.use(http())).thenReturn(httpResult);

		info.setParameters(new Object[] {user.getId()});
	}
	
	@Test
	public void shouldSendHttpNotFoundIfUserNotFound() throws Exception {
		when(users.getUser(user.getId())).thenReturn(null);

		interceptor.intercept(stack, method, result);

		verify(result).notFound();
	}
	
	@Test
	public void shouldContinueIfUserIsEditingHimself() throws Exception {
		when(session.getLoggedUser()).thenReturn(user);

		interceptor.intercept(stack, method, result);

		verify(stack).next(method, result);
	}
	
	@Test
	public void shouldNotAuthorizeWhenModifyingAnotherUser() throws Exception {
		User otherUser = new User();
		otherUser.setId(OTHER_ID);
		
		when(session.getLoggedUser()).thenReturn(otherUser);

		interceptor.intercept(stack, method, result);

		verify(httpResult).sendError(SC_UNAUTHORIZED);
	}

}

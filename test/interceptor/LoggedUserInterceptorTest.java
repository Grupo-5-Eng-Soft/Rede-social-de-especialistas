package interceptor;

import static br.com.caelum.vraptor.view.Results.http;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import infra.UserSession;
import interceptor.annotations.LoggedUser;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.resource.ResourceClass;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.HttpResult;

public class LoggedUserInterceptorTest {
	
	private LoggedUserInterceptor interceptor;
	private @Mock UserSession session;
	private @Mock Result result;
	private @Mock ResourceMethod method;
	private @Mock InterceptorStack stack;
	private @Mock HttpResult http;
	private Object instance;

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		interceptor = new LoggedUserInterceptor(session, result);
		when(result.use(http())).thenReturn(http);
	}
	
	@Test
	public void shouldAcceptsAnnotatedMethod() throws Exception {
		when(method.containsAnnotation(LoggedUser.class)).thenReturn(true);

		assertTrue(interceptor.accepts(method));
	}
	
	@Test
	public void shouldAcceptsAnnotatedClass() throws Exception {
		when(method.getResource()).thenReturn(new LoggedUserAnnotatedClass());
		
		assertTrue(interceptor.accepts(method));
	}
	
	@Test
	public void shouldNotAcceptsNotAnnotatedMethodAndClass() throws Exception {
		when(method.containsAnnotation(LoggedUser.class)).thenReturn(false);
		when(method.getResource()).thenReturn(new ResourceClass() {
			public Class<?> getType() {
				return Object.class;
			}
		});

		assertFalse(interceptor.accepts(method));
	}
	
	@Test
	public void shouldContinueIfUserIsAuthenticated() throws Exception {
		when(session.isAuthenticated()).thenReturn(true);
		
		interceptor.intercept(stack, method, instance);
		
		verify(stack).next(method, instance);
	}
	
	@Test
	public void shouldSendUnauthorizedErrorIfUserIsNotAuthenticated() throws Exception {
		when(session.isAuthenticated()).thenReturn(false);
		
		interceptor.intercept(stack, method, instance);
		
		verify(stack, never()).next(method, instance);
		verify(http).sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}

@LoggedUser
class LoggedUserAnnotatedClass implements ResourceClass {

	@Override
	public Class<?> getType() {
		return LoggedUserAnnotatedClass.class;
	}

}
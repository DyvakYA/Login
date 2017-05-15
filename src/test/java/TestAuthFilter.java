import controller.filters.AuthFilter;
import model.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.AttributesHolder.USER_SESSION_ATTRIBUTE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthFilter {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain chain;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;

    private Filter filter;
    User user;

    @Before
    public void init() throws IOException, ServletException{
        filter = new AuthFilter();
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        doNothing().when(chain).doFilter(request, response);
        user = new User.Builder()
                .setAdmin(true)
                .build();
    }

    private void setUpMocksByParams(String uri, User user){
        when(request.getRequestURI()).thenReturn(uri);
        when(session.getAttribute(USER_SESSION_ATTRIBUTE)).thenReturn(user);
    }

    @Test
    public void testDoFilterToHomePathByNonAuthorizedRequest()throws IOException, ServletException{
        setUpMocksByParams("/home/admin", user);
        filter.doFilter(request, response, chain);
        verify(requestDispatcher, times(0)).forward(request, response);
        verify(request, times(0)).setAttribute(eq(RESULT_ATTRIBUTE), any());
    }

//    @Test
//    public void testDoFilterToStaticResource() throws IOException, ServletException {
//        setUpMocksByParams("script.js", 0, null);
//        filter.doFilter(request, response, chain);
//        verify(requestDispatcher, never()).forward(request, response);
//        verify(chain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    public void testDoFilterAdminPathByNonPrivilegedUser() throws IOException, ServletException {
//        setUpMocksByParams("/admin",1, RoleType.USER);
//        filter.doFilter(request, response, chain);
//        verify(requestDispatcher, times(1)).forward(request, response);
//    }
//
//    @Test
//    public void testDoFilterLoginPathByGuest() throws IOException, ServletException {
//        setUpMocksByParams("/login", 0, null);
//        filter.doFilter(request, response, chain);
//        verify(requestDispatcher, never()).forward(request, response);
//    }
}

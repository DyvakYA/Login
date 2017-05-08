package testController;

import controller.commands.user.LoginCommand;
import controller.commands.validators.user.AuthenticateUserCommandValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static model.constants.AttributesHolder.USER_EMAIL_ATTRIBUTE;
import static model.constants.AttributesHolder.USER_PASSWORD_ATTRIBUTE;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by User on 4/25/2017.
 */
public class testLoginCommand {

    @Mock
    AuthenticateUserCommandValidator authenticateUserCommandValidator;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    LoginCommand command;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        command=new LoginCommand();
    }

    @Test
    public void whenNullUserRedirectToLogin() throws ServletException, IOException {
        when(request.getParameter(USER_EMAIL_ATTRIBUTE)).thenReturn("");
        when(request.getParameter(USER_PASSWORD_ATTRIBUTE)).thenReturn("");
//        verify(request).getRequestDispatcher("pagePost");
        verify(request).getRequestDispatcher(USER_ORDER_DESTINATION_PAGE);
    }

}


package controller.filters;

import model.entities.User;
import model.extras.Localization;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.UrlHolder.INDEX;

public class AuthFilter implements Filter {

    private static final Logger logger=Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        if (isAuthorize(request, response)) {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isAuthorize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        String uri=request.getRequestURI();
        if (user == null) {
            logger.info("User not authorized");
            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().getLocalizedMessage
                            (request, "UserNotAuthorized"));
            request.getRequestDispatcher(INDEX).forward(request, response);
            return false;
        }

        if (((user.isAdmin() == true) & (uri.startsWith("/shop/user")))
                || ((user.isAdmin() == false) & (uri.startsWith("/shop/admin"))
                || (user.isBlocked() == true))) {
            logger.info("Access Denied");
            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().getLocalizedMessage
                            (request, "AccessDenied"));
            request.getRequestDispatcher(INDEX).forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
    }
}









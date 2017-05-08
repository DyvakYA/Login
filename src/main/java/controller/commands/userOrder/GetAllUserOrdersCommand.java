package controller.commands.userOrder;

import controller.commands.Command;
import controller.commands.CommandHelper;
import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static model.constants.AttributesHolder.USER_SESSION_ATTRIBUTE;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class GetAllUserOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_SESSION_ATTRIBUTE);
        CommandHelper.getInstance().ForUserOrderDestinationPage(request, user.getId() );
        return USER_ORDER_DESTINATION_PAGE;
    }
}

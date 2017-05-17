package controller.commands.userOrder;

import controller.commands.Command;
import controller.commands.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.UrlHolder.ORDER_JSP;

/**
 * This class represents get all UserOrder from base command.
 *
 * @author dyvakyurii@gmail.com
 */
public class UserGetAllUserOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        return CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request);
    }
}

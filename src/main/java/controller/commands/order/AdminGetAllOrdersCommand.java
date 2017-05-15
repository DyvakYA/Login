package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.UrlHolder.ORDER_JSP;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class AdminGetAllOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        return CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request);
    }
}

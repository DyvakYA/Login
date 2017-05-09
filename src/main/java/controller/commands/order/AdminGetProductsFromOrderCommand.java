package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;

/**
 *
 *
 * @author dyvakyurii@gmail.com
 */
public class AdminGetProductsFromOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        CommandHelper.getInstance().makeOrdersListForAdminOrderDestinationPage(request);
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}


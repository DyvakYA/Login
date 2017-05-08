package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;

/**
 * Created by User on 4/2/2017.
 */
public class AdminGetProductsFromOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CommandHelper.getInstance().ForAdminOrderDestinationPage(request);
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}


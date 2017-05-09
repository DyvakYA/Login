package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.order.UpdateOrderCommandValidator;
import model.entities.Order;
import model.extras.Localization;
import model.services.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.UPDATE_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

/**
 *
 *
 * @author dyvakyurii@gmail.com
 */
public class AdminUpdateOrderCommand implements Command {

    private OrderService orderService=OrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new UpdateOrderCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        Order order= new Order.Builder()
                .setOrderStatus(request.getParameter(ORDER_STATUS_ATTRIBUTE))
                .build();
        orderService.update(order, Integer.valueOf(request.getParameter(ORDER_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, UPDATE_ORDER_SUCCESSFUL_MSG));
        CommandHelper.getInstance().makeOrdersListForAdminOrderDestinationPage(request);
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}

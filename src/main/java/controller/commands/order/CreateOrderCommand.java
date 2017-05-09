package controller.commands.order;

import controller.commands.Command;
import controller.commands.validators.order.CreateOrderCommandValidator;
import model.constants.AttributesHolder;
import model.entities.Order;
import model.extras.Localization;
import model.services.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.CREATE_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

/**
 *
 *
 * @author dyvakyurii@gmail.com
 */
public class CreateOrderCommand implements Command {

    private OrderService orderService=OrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new CreateOrderCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        Order order = new Order.Builder()
                .setOrderStatus(request.getParameter(ORDER_STATUS_ATTRIBUTE))
                .setDate(new Date())
                .build();
        orderService.create(order);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, CREATE_ORDER_SUCCESSFUL_MSG));
        request.setAttribute(AttributesHolder.ORDERS_LIST_ATTRIBUTE, orderService.getAll());
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}

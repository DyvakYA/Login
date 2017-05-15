package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.order.UpdateOrderStatusCommandValidator;
import model.entities.Order;
import model.extras.Localization;
import model.services.OrderService;
import model.services.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.UPDATE_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ORDER_JSP;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class AdminUpdateOrderStatusCommand implements Command {

    private OrderService orderService=OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new UpdateOrderStatusCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        Order order= new Order.Builder()
                .setOrderId(Integer.valueOf(request.getParameter(ORDER_ID_ATTRIBUTE)))
                .setOrderStatus(request.getParameter(ORDER_STATUS_ATTRIBUTE))
                .build();
        orderService.updateOrderStatus(order, Integer.parseInt((request.getParameter(ORDER_ID_ATTRIBUTE))));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, UPDATE_ORDER_SUCCESSFUL_MSG));
        return CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request);
    }
}
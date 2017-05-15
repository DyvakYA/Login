package controller.commands.order;

import controller.commands.Command;
import controller.commands.validators.order.UpdateOrderCommandValidator;
import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;
import model.extras.Localization;
import model.services.service.OrderProductService;
import model.services.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.UPDATE_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class AdminUpdateOrderCommand implements Command {

    private OrderService orderService=OrderService.getInstance();
    private OrderProductService orderProductService=OrderProductService.getInstance();

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
        List<Order> orderList = orderService.getAll();
        Map<Order, Map<OrderProduct, Product>> orderMap = orderProductService.getOrdersMap(orderList);
        request.setAttribute(ORDER_MAP_ATTRIBUTE, orderMap);
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}

package controller.commands.userOrder;

import controller.commands.Command;
import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;
import model.entities.User;
import model.services.service.OrderProductService;
import model.services.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static model.constants.AttributesHolder.ORDER_MAP_ATTRIBUTE;
import static model.constants.AttributesHolder.USER_SESSION_ATTRIBUTE;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class UserGetAllUserOrdersCommand implements Command {

    private OrderProductService orderProductService=OrderProductService.getInstance();
    private UserOrderService userOrderService=UserOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_SESSION_ATTRIBUTE);

        List<Order> orderList=userOrderService.getOrdersForUser(user.getId());
        Map<Order, Map<OrderProduct, Product>> orderMap = orderProductService.getOrdersMap(orderList);

        request.setAttribute(ORDER_MAP_ATTRIBUTE, orderMap);
        return USER_ORDER_DESTINATION_PAGE;
    }
}

package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.order.DeleteOrderCommandValidator;
import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;
import model.entities.User;
import model.extras.Localization;
import model.services.service.OrderService;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.DELETE_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ORDER;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class DeleteOrderCommand implements Command {

    private OrderService orderService=OrderService.getInstance();
    private UserService userService=UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new DeleteOrderCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        orderService.delete(Integer.parseInt(request.getParameter(ORDER_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, DELETE_ORDER_SUCCESSFUL_MSG));
        List<User> userList = userService.getAllUsersWithOrders();
        Map<User ,Map<Order, Map<OrderProduct, Product>>> userMap = userService.getUserMap(userList);
        request.setAttribute(USER_MAP_ATTRIBUTE, userMap);
        return CommandHelper.getInstance().roleChecker(ORDER, request);
    }
}

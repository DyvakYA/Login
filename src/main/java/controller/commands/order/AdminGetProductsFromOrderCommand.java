package controller.commands.order;

import controller.commands.Command;
import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;
import model.entities.User;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static model.constants.AttributesHolder.USER_MAP_ATTRIBUTE;
import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class AdminGetProductsFromOrderCommand implements Command {

    private UserService userService=UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<User> userList = userService.getAllUsersWithOrders();
        Map<User ,Map<Order, Map<OrderProduct, Product>>> userMap = userService.getUserMap(userList);
        request.setAttribute(USER_MAP_ATTRIBUTE, userMap);
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}


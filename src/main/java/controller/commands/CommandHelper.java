package controller.commands;

import model.entities.Order;
import model.entities.Product;
import model.entities.User;
import model.services.service.OrderProductService;
import model.services.service.OrderService;
import model.services.service.UserOrderService;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static model.constants.AttributesHolder.*;
import static model.constants.UrlHolder.*;

/**
 * Created by User on 4/27/2017.
 */
public class CommandHelper {

    private OrderService orderService=OrderService.getInstance();
    private OrderProductService orderProductService=OrderProductService.getInstance();
    private UserOrderService userOrderService=UserOrderService.getInstance();
    private UserService userService=UserService.getInstance();

    private static class Holder {
        static final CommandHelper INSTANCE = new CommandHelper();
    }

    public static CommandHelper getInstance() {
        return CommandHelper.Holder.INSTANCE;
    }

    public void makeOrdersListForAdminOrderDestinationPage(HttpServletRequest request) {

        List<Order> orderList=orderService.getAll();
        Map<Order, List<Product>> orderMap=new HashMap<>();
        for (Order order : orderList) {
            orderMap.put(order, orderProductService.getAllProductsOnOrder(order.getOrderId()));
        }
        request.setAttribute(ORDER_MAP_ATTRIBUTE, orderMap);
    }

    public void makeOrdersListForUserOrderDestinationPage(HttpServletRequest request, int id) {

        List<Order> orderList=userOrderService.getOrdersForUser(id);
        Map<Order, List<Product>> orderMap=new HashMap<>();
        for (Order order : orderList) {
            orderMap.put(order, orderProductService.getAllProductsOnOrder(order.getOrderId()));
        }
        request.setAttribute(ORDER_MAP_ATTRIBUTE, orderMap);
    }

    public String isUser(HttpServletRequest request, String destinationPage, User user) {
        if (!user.isAdmin()) {
            makeOrdersListForUserOrderDestinationPage(request, user.getId());
            return USER_ORDER_DESTINATION_PAGE;
        }
        return destinationPage;
    }

    public String isAdmin(HttpServletRequest request, String destinationPage, User user) {
        if (user.isAdmin()) {
            request.setAttribute(USERS_LIST_ATTRIBUTE, userService.getAll());
            return ADMIN_USERS_DESTINATION_PAGE;
        }
        return destinationPage;
    }

    public String roleChecker(String destinationPage, HttpServletRequest request) {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_SESSION_ATTRIBUTE);
        if (user == null) {
            return GUEST + destinationPage;
        }
        if (!user.isAdmin()) {
            return USER + destinationPage;
        }
        if (user.isAdmin()) {
            return ADMIN + destinationPage;
        }
        return null;
    }
}

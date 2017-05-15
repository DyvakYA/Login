package controller.commands;

import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;
import model.entities.User;
import model.services.service.OrderProductService;
import model.services.service.UserOrderService;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static model.constants.AttributesHolder.ORDER_MAP_ATTRIBUTE;
import static model.constants.AttributesHolder.USERS_LIST_ATTRIBUTE;
import static model.constants.AttributesHolder.USER_SESSION_ATTRIBUTE;
import static model.constants.UrlHolder.*;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class CommandHelper {

    private OrderProductService orderProductService=OrderProductService.getInstance();
    private UserOrderService userOrderService=UserOrderService.getInstance();
    private UserService userService=UserService.getInstance();

    private static class Holder {
        static final CommandHelper INSTANCE=new CommandHelper();
    }

    public static CommandHelper getInstance() {
        return CommandHelper.Holder.INSTANCE;
    }

    /**
     *  This role checker check the role of authorized user
     *  and depending on it return destination page
     */
    public String roleChecker(String destinationPage, HttpServletRequest request) {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_SESSION_ATTRIBUTE);
        if (user == null) {
            return GUEST + destinationPage;
        }
        if (!user.isAdmin()) {
            return WEB_INF_USER + destinationPage;
        }
        if (user.isAdmin()) {
            return WEB_INF_ADMIN + destinationPage;
        }
        return null;
    }

    /**
     *  This role checker check the role of authorized user
     *  and depending on it make some action, set attributes
     *  and return destination page.
     */
    public String getDestinationPageByRole(HttpServletRequest request, User user) {
        if (user.isAdmin()) {
            request.setAttribute(USERS_LIST_ATTRIBUTE, userService.getAll());
            return ADMIN_USERS_DESTINATION_PAGE;
        } else if (!user.isAdmin()) {
            List<Order> orderList=userOrderService.getOrdersForUser(user.getId());
            Map<Order, Map<OrderProduct, Product>> orderMap=orderProductService.getOrdersMap(orderList);
            request.setAttribute(ORDER_MAP_ATTRIBUTE, orderMap);
            return USER_ORDER_DESTINATION_PAGE;
        }
        return null;
    }
}

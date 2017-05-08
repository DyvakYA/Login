package controller.commands.user;

import controller.commands.Command;
import controller.commands.CommandHelper;
import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.User;
import model.entities.UserOrder;
import model.extras.Localization;
import model.services.service.OrderProductService;
import model.services.service.OrderService;
import model.services.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.CREATE_USER_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.INDEX;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

/**
 * Created by User on 4/23/2017.
 */
public class UserAddProductToOrderCommand implements Command {

    private OrderService orderService=OrderService.getInstance();
    private UserOrderService userOrderService=UserOrderService.getInstance();
    private OrderProductService orderProductService=OrderProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_SESSION_ATTRIBUTE);

        if (user == null) {
            request.setAttribute(RESULT_ATTRIBUTE,
                    Localization.getInstanse().getLocalizedMessage
                            (request, "UserNotAuthorized"));
            return INDEX;
        }
        else {
            if(session.getAttribute(ORDER_ID_ATTRIBUTE) == null){
                Order order=new Order.Builder()
                        .setOrderStatus("started")
                        .setDate(new Date())
                        .build();
                orderService.create(order);
                session.setAttribute(ORDER_ID_ATTRIBUTE, order.getOrderId());
                UserOrder userOrder=new UserOrder.Builder()
                        .setUserId(user.getId())
                        .setOrderId(order.getOrderId())
                        .build();
                userOrderService.create(userOrder);
                OrderProduct orderProduct=new OrderProduct.Builder()
                        .setOrderId(order.getOrderId())
                        .setProductId(Integer.parseInt(request.getParameter(PRODUCT_ID_ATTRIBUTE)))
                        .setQuantity(Integer.parseInt(request.getParameter(QUANTITY)))
                        .build();
                orderProductService.create(orderProduct);
                request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                        .getLocalizedMessage(request, CREATE_USER_ORDER_SUCCESSFUL_MSG));
            }
            if (session.getAttribute(ORDER_ID_ATTRIBUTE) != null) {
                Optional<OrderProduct> orderProductFromBase=orderProductService.getOrderProductByOrderIdAndProductId(
                        Integer.parseInt(String.valueOf(session.getAttribute(ORDER_ID_ATTRIBUTE))),
                        Integer.parseInt(request.getParameter(PRODUCT_ID_ATTRIBUTE)));
                if (orderProductFromBase.isPresent()) {
                    OrderProduct orderProduct = orderProductFromBase.get();
                    orderProduct.setQuantity(orderProduct.getQuantity()+Integer.parseInt(request.getParameter(QUANTITY)));
                    orderProductService.update(orderProduct,orderProduct.getId());
                }
                if (!orderProductFromBase.isPresent()) {
                    OrderProduct orderProduct=new OrderProduct.Builder()
                            .setOrderId(Integer.parseInt(String.valueOf(session.getAttribute(ORDER_ID_ATTRIBUTE))))
                            .setProductId(Integer.parseInt(request.getParameter(PRODUCT_ID_ATTRIBUTE)))
                            .setQuantity(Integer.parseInt(request.getParameter(QUANTITY)))
                            .build();
                    orderProductService.create(orderProduct);
                    request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                            .getLocalizedMessage(request, CREATE_USER_ORDER_SUCCESSFUL_MSG));
                }
            }

        }
        CommandHelper.getInstance().ForUserOrderDestinationPage(request, user.getId());
        return USER_ORDER_DESTINATION_PAGE;
    }
}

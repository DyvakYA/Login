package controller.commands.userOrder;

import controller.commands.Command;
import controller.commands.validators.product.UpdateUserOrderCommandValidator;
import model.entities.UserOrder;
import model.extras.Localization;
import model.services.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.UPDATE_USER_ORDERS_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.REDIRECTED;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class UpdateUserOrderCommand implements Command {

    private UserOrderService userOrderService = UserOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new UpdateUserOrderCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        UserOrder userOrder = new UserOrder.Builder()
                .setUserId(Integer.parseInt(USER_ID_ATTRIBUTE))
                .setOrderId(Integer.parseInt(ORDER_ID_ATTRIBUTE))
                .build();
        userOrderService.update(userOrder, userOrder.getOrderId());
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, UPDATE_USER_ORDERS_SUCCESSFUL_MSG));
        request.setAttribute(USER_ORDERS_LIST_ATTRIBUTE, userOrderService.getAll());
        return USER_ORDER_DESTINATION_PAGE;
    }

}

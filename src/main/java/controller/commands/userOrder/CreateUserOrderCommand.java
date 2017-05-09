package controller.commands.userOrder;

import controller.commands.Command;
import controller.commands.validators.product.CreateProductCommandValidator;
import model.entities.UserOrder;
import model.extras.Localization;
import model.services.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.CREATE_USER_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.REDIRECTED;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

public class CreateUserOrderCommand implements Command {

    private UserOrderService userOrderService=UserOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new CreateProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        UserOrder userOrder = new UserOrder.Builder()
                .setUserId(Integer.valueOf(request.getParameter(USER_ID_ATTRIBUTE)))
                .setOrderId(Integer.valueOf(request.getParameter(ORDER_ID_ATTRIBUTE)))
                .build();
        userOrderService.create(userOrder);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, CREATE_USER_ORDER_SUCCESSFUL_MSG));
        request.setAttribute(USER_ORDERS_LIST_ATTRIBUTE, userOrderService.getAll());
        return USER_ORDER_DESTINATION_PAGE;
    }
}

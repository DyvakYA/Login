package controller.commands.userOrder;

import controller.commands.Command;
import controller.commands.validators.product.DeleteProductCommandValidator;
import model.extras.Localization;
import model.services.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.DELETE_USER_ORDERS_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.REDIRECTED;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

public class DeleteUserOrderCommand implements Command {

    private UserOrderService userOrderService = UserOrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new DeleteProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        userOrderService.delete(Integer.valueOf(request.getParameter(USER_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_USER_ORDERS_SUCCESSFUL_MSG));
        request.setAttribute(USER_ORDERS_LIST_ATTRIBUTE, userOrderService.getAll());
        return USER_ORDER_DESTINATION_PAGE;
    }
}
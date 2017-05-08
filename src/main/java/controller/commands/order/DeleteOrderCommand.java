package controller.commands.order;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.order.DeleteOrderCommandValidator;
import model.extras.Localization;
import model.services.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.ORDER_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.MsgHolder.DELETE_ORDER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ORDER;
import static model.constants.UrlHolder.REDIRECTED;

public class DeleteOrderCommand implements Command {

    private OrderService orderService=OrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        if (!new DeleteOrderCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        int id= Integer.parseInt(request.getParameter(ORDER_ID_ATTRIBUTE));
        orderService.delete(id);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_ORDER_SUCCESSFUL_MSG));
        CommandHelper.getInstance().ForAdminOrderDestinationPage(request);
        return CommandHelper.getInstance().roleChecker(ORDER, request);
    }
}

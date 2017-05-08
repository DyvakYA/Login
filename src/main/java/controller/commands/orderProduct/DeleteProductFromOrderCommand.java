package controller.commands.orderProduct;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.product.DeleteProductCommandValidator;
import model.extras.Localization;
import model.services.service.OrderProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.ORDER_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.PRODUCT_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.MsgHolder.DELETE_PRODUCT_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ORDER;
import static model.constants.UrlHolder.REDIRECTED;

public class DeleteProductFromOrderCommand implements Command {

    private OrderProductService orderProductService=OrderProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!new DeleteProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        int orderId= Integer.parseInt(request.getParameter(ORDER_ID_ATTRIBUTE));
        int productId= Integer.parseInt(request.getParameter(PRODUCT_ID_ATTRIBUTE));
        orderProductService.deleteProductFromOrder(orderId, productId);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, DELETE_PRODUCT_SUCCESSFUL_MSG));
        CommandHelper.getInstance().ForAdminOrderDestinationPage(request);
        return CommandHelper.getInstance().roleChecker(ORDER, request);
    }
}

package controller.commands.orderProduct;

import controller.commands.Command;
import controller.commands.validators.orderProduct.CreateOrderProductCommandValidator;
import model.entities.OrderProduct;
import model.extras.Localization;
import model.services.service.OrderProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.CREATE_ORDER_PRODUCTS_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ORDER_PRODUCT_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

public class CreateOrderProductCommand implements Command {

    private OrderProductService orderProductService=OrderProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new CreateOrderProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        OrderProduct orderProduct = new OrderProduct.Builder()
                .setOrderId(Integer.valueOf(request.getParameter(ORDER_ID_ATTRIBUTE)))
                .setProductId(Integer.valueOf(request.getParameter(PRODUCT_ID_ATTRIBUTE)))
                .build();
        orderProductService.create(orderProduct);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, CREATE_ORDER_PRODUCTS_SUCCESSFUL_MSG));
        request.setAttribute(ORDER_PRODUCTS_LIST_ATTRIBUTE, orderProductService.getAll());
        return ORDER_PRODUCT_DESTINATION_PAGE;
    }
}

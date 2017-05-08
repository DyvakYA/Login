package controller.commands.orderProduct;

import controller.commands.Command;
import controller.commands.validators.orderProduct.UpdateOrderProductCommandValidator;
import model.entities.OrderProduct;
import model.extras.Localization;
import model.services.service.OrderProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.UPDATE_ORDER_PRODUCTS_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ORDER_PRODUCT_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

public class UpdateOrderProductCommand implements Command {

    private OrderProductService orderProductsService=OrderProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new UpdateOrderProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        OrderProduct orderProduct= new OrderProduct.Builder()
                .setOrderId(Integer.valueOf(request.getParameter(ORDER_ID_ATTRIBUTE)))
                .setProductId(Integer.valueOf(request.getParameter(PRODUCT_ID_ATTRIBUTE)))
                .build();
        orderProductsService.update(orderProduct, Integer.valueOf(request.getParameter(ORDER_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_ORDER_PRODUCTS_SUCCESSFUL_MSG));
        request.setAttribute(ORDER_PRODUCTS_LIST_ATTRIBUTE, orderProductsService.getAll());
        return ORDER_PRODUCT_DESTINATION_PAGE;
    }

}

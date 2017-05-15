package controller.commands.orderProduct;

import controller.commands.Command;
import model.entities.OrderProduct;
import model.services.service.OrderProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.constants.AttributesHolder.ORDER_PRODUCTS_LIST_ATTRIBUTE;
import static model.constants.UrlHolder.ORDER_PRODUCT_DESTINATION_PAGE;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class GetAllOrderProductCommand implements Command {

    private OrderProductService orderProductsService=OrderProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<OrderProduct> orderProducts=orderProductsService.getAll();
        request.setAttribute(ORDER_PRODUCTS_LIST_ATTRIBUTE, orderProducts);
        return ORDER_PRODUCT_DESTINATION_PAGE;
    }
}

package controller.commands.order;

import controller.commands.Command;
import model.services.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.ORDER_LIST_ATTRIBUTE;
import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;

public class AdminGetAllOrdersCommand implements Command {

    private OrderService orderService=OrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setAttribute(ORDER_LIST_ATTRIBUTE, orderService.getAll());
        return ADMIN_ORDER_DESTINATION_PAGE;
    }
}
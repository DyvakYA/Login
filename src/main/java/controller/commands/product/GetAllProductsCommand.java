package controller.commands.product;

import controller.commands.Command;
import controller.commands.CommandHelper;
import model.services.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.PRODUCTS_LIST_ATTRIBUTE;
import static model.constants.UrlHolder.PRODUCT;

/**
 * Created by User on 4/7/2017.
 */
public class GetAllProductsCommand implements Command {

    private ProductService productService=ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, productService.getAll());
        return CommandHelper.getInstance().roleChecker(PRODUCT, request);
    }
}

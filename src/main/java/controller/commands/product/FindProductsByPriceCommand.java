package controller.commands.product;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.product.FindProductsByPriceCommandValidator;
import model.entities.Product;
import model.services.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.constants.AttributesHolder.*;
import static model.constants.UrlHolder.PRODUCT;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class FindProductsByPriceCommand implements Command {

    private ProductService productService=ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new FindProductsByPriceCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        List<Product> products=productService.getProductsByPrice(
                Integer.parseInt(request.getParameter(PRICE_FIRST_ATTRIBUTE)),
                Integer.parseInt(request.getParameter(PRICE_SECOND_ATTRIBUTE)));
        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, products);
        return CommandHelper.getInstance()
                .roleChecker(PRODUCT, request);
    }
}


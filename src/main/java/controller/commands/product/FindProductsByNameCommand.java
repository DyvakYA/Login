package controller.commands.product;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.product.FindProductsByNameCommandValidator;
import model.entities.Product;
import model.services.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.constants.AttributesHolder.PRODUCTS_LIST_ATTRIBUTE;
import static model.constants.AttributesHolder.PRODUCT_NAME_ATTRIBUTE;
import static model.constants.UrlHolder.PRODUCT;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * Created by User on 5/2/2017.
 */
public class FindProductsByNameCommand implements Command {

    private ProductService productService=ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new FindProductsByNameCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        List<Product> products=productService.getProductsByName(request.getParameter(PRODUCT_NAME_ATTRIBUTE));
        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, products);
        return CommandHelper.getInstance()
                .roleChecker(PRODUCT, request);
    }
}


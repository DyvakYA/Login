package controller.commands.product;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.product.FindProductsByNameCommandValidator;
import model.entities.Product;
import model.services.ProductService;
import model.services.service.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.constants.AttributesHolder.PRODUCTS_LIST_ATTRIBUTE;
import static model.constants.AttributesHolder.PRODUCT_NAME_ATTRIBUTE;
import static model.constants.UrlHolder.PRODUCT_JSP;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class FindProductsByNameCommand implements Command {

    private ProductService productService=ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new FindProductsByNameCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        List<Product> products=productService.getProductsByName(request.getParameter(PRODUCT_NAME_ATTRIBUTE));
        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, products);
        return CommandHelper.getInstance().roleCheckerDestinationPageReturner(PRODUCT_JSP, request);
    }
}
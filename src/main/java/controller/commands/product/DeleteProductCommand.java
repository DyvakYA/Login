package controller.commands.product;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.product.DeleteProductCommandValidator;
import model.extras.Localization;
import model.services.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.DELETE_PRODUCT_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.PRODUCT;
import static model.constants.UrlHolder.REDIRECTED;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class DeleteProductCommand implements Command {

    private ProductService productService=ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new DeleteProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        productService.delete(Integer.parseInt(request.getParameter(PRODUCT_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, DELETE_PRODUCT_SUCCESSFUL_MSG));
        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, productService.getAll());
        return CommandHelper.getInstance().roleChecker(PRODUCT, request);
    }
}

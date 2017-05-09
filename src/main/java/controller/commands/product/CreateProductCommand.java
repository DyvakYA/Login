package controller.commands.product;

import controller.commands.Command;
import controller.commands.validators.product.CreateProductCommandValidator;
import model.entities.Product;
import model.extras.Localization;
import model.services.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.CREATE_PRODUCT_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_PRODUCT_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

public class CreateProductCommand implements Command {

    private ProductService productService=ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new CreateProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        Product product = new Product.Builder()
                .setName(request.getParameter(PRODUCT_NAME_ATTRIBUTE))
                .setDescription(request.getParameter(PRODUCT_DESCRIPTION_ATTRIBUTE))
                .setPrice(Long.parseLong(request.getParameter(PRODUCT_PRICE_ATTRIBUTE)))
                .build();
        productService.create(product);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
                .getLocalizedMessage(request, CREATE_PRODUCT_SUCCESSFUL_MSG));
        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, productService.getAll());
        return ADMIN_PRODUCT_DESTINATION_PAGE;
    }
}

package controller.commands.product;

import controller.commands.Command;
import controller.commands.validators.product.UpdateProductCommandValidator;
import model.entities.Product;
import model.extras.Localization;
import model.services.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.UPDATE_PRODUCT_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_PRODUCT_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

public class UpdateProductCommand implements Command {

    private ProductService productService=ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new UpdateProductCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        Product product=new Product.Builder()
                .setName(request.getParameter(PRODUCT_NAME_ATTRIBUTE))
                .setDescription(request.getParameter(PRODUCT_DESCRIPTION_ATTRIBUTE))
                .setPrice(Integer.parseInt(request.getParameter(PRODUCT_PRICE_ATTRIBUTE)))
                .build();
        productService.update(product, Integer.parseInt(request.getParameter(PRODUCT_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, UPDATE_PRODUCT_SUCCESSFUL_MSG));
        request.setAttribute(PRODUCTS_LIST_ATTRIBUTE, productService.getAll());
        return ADMIN_PRODUCT_DESTINATION_PAGE;
    }

}
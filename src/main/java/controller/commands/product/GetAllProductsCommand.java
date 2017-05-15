package controller.commands.product;

import controller.commands.Command;
import controller.commands.CommandHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.UrlHolder.PRODUCT_JSP;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class GetAllProductsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        return CommandHelper.getInstance().roleCheckerSetAttributes(PRODUCT_JSP, request);
    }
}

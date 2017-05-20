package controller.commands.validators.product;

import controller.commands.validators.AbstractValidator;
import controller.commands.validators.CommandValidator;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.PRODUCT_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.PRODUCT_JSP;

public class DeleteProductCommandValidator extends AbstractValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

         String message = Localization.getInstance().getLocalizedMessage(request, PRODUCT_ERROR_MSG);
         
        return isNullValidate(new String[]{PRODUCT_ID_ATTRIBUTE}, RESULT_ATTRIBUTE,
                roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response)
                &&
                isEmptyValidate(new String[]{PRODUCT_ID_ATTRIBUTE}, RESULT_ATTRIBUTE,
                roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response);
    }
}

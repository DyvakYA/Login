package controller.commands.validators.product;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.PRODUCT_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.PRODUCT_JSP;

public class DeleteProductCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

         String message = Localization.getInstance().getLocalizedMessage(request, PRODUCT_ERROR_MSG);
         
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRODUCT_ID_ATTRIBUTE}, RESULT_ATTRIBUTE,
                CommandHelper.getInstance().roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response)
                &&
                CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{PRODUCT_ID_ATTRIBUTE}, RESULT_ATTRIBUTE,
                CommandHelper.getInstance().roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response);
    }
}

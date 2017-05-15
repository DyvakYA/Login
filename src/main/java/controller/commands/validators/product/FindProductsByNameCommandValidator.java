package controller.commands.validators.product;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.PRODUCT_NAME_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.INCORRECT_VALUE_PRODUCT_NAME;
import static model.constants.UrlHolder.ADMIN_PRODUCT_DESTINATION_PAGE;
import static model.constants.UrlHolder.PRODUCT_JSP;

public class FindProductsByNameCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, INCORRECT_VALUE_PRODUCT_NAME);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRODUCT_NAME_ATTRIBUTE},
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{PRODUCT_NAME_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_PRODUCT_DESTINATION_PAGE, message, request, response);
    }
}
package controller.commands.validators.product;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.FIND_PRICE_ERROR_MSG;
import static model.constants.UrlHolder.ADMIN_PRODUCT_DESTINATION_PAGE;
import static model.constants.UrlHolder.PRODUCT_JSP;

public class FindProductsByPriceCommandValidator implements CommandValidator {

    private static final String REGEX="^[0-9][0-9]*$";

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, FIND_PRICE_ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRICE_FIRST_ATTRIBUTE, PRICE_SECOND_ATTRIBUTE},
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{PRICE_FIRST_ATTRIBUTE, PRICE_SECOND_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_PRODUCT_DESTINATION_PAGE, message, request, response)
                && CommandValidatorHelper.getInstance().matchesValidate(new String[]{PRICE_FIRST_ATTRIBUTE, PRICE_SECOND_ATTRIBUTE}, REGEX,
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(PRODUCT_JSP, request), message, request, response);
    }
}
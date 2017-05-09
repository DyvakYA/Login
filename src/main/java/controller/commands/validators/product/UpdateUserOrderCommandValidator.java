package controller.commands.validators.product;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

public class UpdateUserOrderCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, PRODUCT_ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRODUCT_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, USER_ORDER_DESTINATION_PAGE, message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{PRODUCT_NAME_ATTRIBUTE, PRODUCT_PRICE_ATTRIBUTE},
                RESULT_ATTRIBUTE, USER_ORDER_DESTINATION_PAGE, message, request, response);
    }

}

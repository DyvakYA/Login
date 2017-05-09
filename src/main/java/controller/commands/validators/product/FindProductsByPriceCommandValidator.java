package controller.commands.validators.product;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.PRODUCT;

public class FindProductsByPriceCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, PRODUCT_ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRICE_FIRST_ATTRIBUTE, PRICE_SECOND_ATTRIBUTE},
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleChecker(PRODUCT, request), message, request, response);
    }
}
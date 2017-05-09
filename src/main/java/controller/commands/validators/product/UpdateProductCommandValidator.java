package controller.commands.validators.product;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.PRODUCT_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.ADMIN_PRODUCT_DESTINATION_PAGE;

public class UpdateProductCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, PRODUCT_ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRODUCT_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_PRODUCT_DESTINATION_PAGE, message, request, response);
    }
}
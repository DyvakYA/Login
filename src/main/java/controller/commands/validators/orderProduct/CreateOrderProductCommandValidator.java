package controller.commands.validators.orderProduct;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.PRODUCT_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.ORDER_PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.ORDER_PRODUCT_DESTINATION_PAGE;

public class CreateOrderProductCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, ORDER_PRODUCT_ERROR_MSG);

        return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{PRODUCT_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ORDER_PRODUCT_DESTINATION_PAGE, message, request, response);
    }
}
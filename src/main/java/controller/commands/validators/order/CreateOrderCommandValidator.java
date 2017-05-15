package controller.commands.validators.order;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.ORDER_ERROR_MSG;
import static model.constants.UrlHolder.ADMIN_ORDER_DESTINATION_PAGE;

public class CreateOrderCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, ORDER_ERROR_MSG);

        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{},
                RESULT_ATTRIBUTE, ADMIN_ORDER_DESTINATION_PAGE, message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{},
                RESULT_ATTRIBUTE, ADMIN_ORDER_DESTINATION_PAGE, message, request, response);
    }
}

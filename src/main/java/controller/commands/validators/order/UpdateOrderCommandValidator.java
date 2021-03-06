package controller.commands.validators.order;

import controller.commands.validators.AbstractValidator;
import controller.commands.validators.CommandValidator;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.ORDER_ID_ATTRIBUTE;
import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.ORDER_ERROR_MSG;
import static model.constants.UrlHolder.ORDER_JSP;

public class UpdateOrderCommandValidator extends AbstractValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, ORDER_ERROR_MSG);

        return isNullValidate(new String[]{ORDER_ID_ATTRIBUTE}, RESULT_ATTRIBUTE, roleCheckerSetAttributes(ORDER_JSP,request), message, request, response)
                &&
                isEmptyValidate(new String[]{ORDER_ID_ATTRIBUTE}, RESULT_ATTRIBUTE, roleCheckerSetAttributes(ORDER_JSP,request), message, request, response);
    }
}

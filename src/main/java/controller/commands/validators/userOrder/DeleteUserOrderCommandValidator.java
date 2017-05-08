package controller.commands.validators.userOrder;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.USER_ORDER_ERROR_MSG;
import static model.constants.UrlHolder.USER_ORDER_DESTINATION_PAGE;

public class DeleteUserOrderCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstanse().getLocalizedMessage(request, USER_ORDER_ERROR_MSG);

        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{USER_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE,  USER_ORDER_DESTINATION_PAGE, message, request, response);
    }

}

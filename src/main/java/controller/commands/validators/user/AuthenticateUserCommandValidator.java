package controller.commands.validators.user;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.NOT_VALID_EMPTY_LOGIN_AND_AUTHENTICATE;
import static model.constants.UrlHolder.ORDER_JSP;

public class AuthenticateUserCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, NOT_VALID_EMPTY_LOGIN_AND_AUTHENTICATE);

        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{USER_EMAIL_ATTRIBUTE, USER_AUTHENTICATE_ATTRIBUTE},
                RESULT_ATTRIBUTE,  CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request), message, request, response)
                &&
                CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{USER_EMAIL_ATTRIBUTE, USER_AUTHENTICATE_ATTRIBUTE},
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request), message, request, response);
    }
}

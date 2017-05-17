package controller.commands.validators.user;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.RESULT_ATTRIBUTE;
import static model.constants.AttributesHolder.USER_ID_ATTRIBUTE;
import static model.constants.ErrorMsgHolder.USER_ERROR_MSG;
import static model.constants.UrlHolder.USER_JSP;

public class DeleteUserCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, USER_ERROR_MSG);
         
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{USER_ID_ATTRIBUTE}, RESULT_ATTRIBUTE,
                CommandHelper.getInstance().roleCheckerSetAttributes(USER_JSP, request), message, request, response)
                &&
                CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{USER_ID_ATTRIBUTE}, RESULT_ATTRIBUTE,
                CommandHelper.getInstance().roleCheckerSetAttributes(USER_JSP, request), message, request, response);
    }
}

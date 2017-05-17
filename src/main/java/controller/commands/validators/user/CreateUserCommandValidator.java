package controller.commands.validators.user;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.ENTER_EMAIL_AND_AUTHENTICATE_WORD_MSG;
import static model.constants.UrlHolder.USER_JSP;

public class CreateUserCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, ENTER_EMAIL_AND_AUTHENTICATE_WORD_MSG);
        
        return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{USER_EMAIL_ATTRIBUTE, USER_AUTHENTICATE_ATTRIBUTE},
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(USER_JSP, request), message, request, response);
    }
    
}

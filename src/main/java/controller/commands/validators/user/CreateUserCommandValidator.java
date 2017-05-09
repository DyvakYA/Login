package controller.commands.validators.user;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.USER_ERROR_MSG;
import static model.constants.UrlHolder.ADMIN_USERS_DESTINATION_PAGE;

public class CreateUserCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message = Localization.getInstance().getLocalizedMessage(request, USER_ERROR_MSG);
        
        return CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{USER_EMAIL_ATTRIBUTE, USER_AUTHENTICATE_ATTRIBUTE},
                RESULT_ATTRIBUTE, ADMIN_USERS_DESTINATION_PAGE, message, request, response);
    }
    
}

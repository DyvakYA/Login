package controller.commands.validators.user;

import controller.commands.CommandHelper;
import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.USER_ERROR_MSG;
import static model.constants.UrlHolder.ORDER_JSP;

/**
 * Created by User on 5/14/2017.
 */
public class UserAddProductToOrderValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message=Localization.getInstance().getLocalizedMessage(request, USER_ERROR_MSG);

        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{PRODUCT_ID_ATTRIBUTE, QUANTITY},
                RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request), message, request, response)
                &&
                CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{PRODUCT_ID_ATTRIBUTE, QUANTITY},
                        RESULT_ATTRIBUTE, CommandHelper.getInstance().roleCheckerSetAttributes(ORDER_JSP, request), message, request, response);
    }
}

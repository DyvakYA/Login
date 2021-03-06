package controller.commands.validators.user;

import controller.commands.validators.AbstractValidator;
import controller.commands.validators.CommandValidator;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.USER_ERROR_MSG;
import static model.constants.UrlHolder.ORDER_JSP;

/**
 * Created by User on 5/14/2017.
 */
public class UserAddProductToOrderValidator extends AbstractValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message=Localization.getInstance().getLocalizedMessage(request, USER_ERROR_MSG);

        return isNullValidate(new String[]{PRODUCT_ID_ATTRIBUTE, QUANTITY},
                RESULT_ATTRIBUTE, roleCheckerSetAttributes(ORDER_JSP, request), message, request, response)
                &&
                isEmptyValidate(new String[]{PRODUCT_ID_ATTRIBUTE, QUANTITY},
                        RESULT_ATTRIBUTE, roleCheckerSetAttributes(ORDER_JSP, request), message, request, response);
    }
}

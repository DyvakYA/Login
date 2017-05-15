/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.validators.orderProduct;

import controller.commands.validators.CommandValidator;
import controller.commands.validators.CommandValidatorHelper;
import model.extras.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.constants.AttributesHolder.*;
import static model.constants.ErrorMsgHolder.ORDER_PRODUCT_ERROR_MSG;
import static model.constants.UrlHolder.ORDER_PRODUCT_DESTINATION_PAGE;

public class UpdateOrderProductCommandValidator implements CommandValidator {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

        String message=Localization.getInstance().getLocalizedMessage(request, ORDER_PRODUCT_ERROR_MSG);

        return (CommandValidatorHelper.getInstance().isNullValidate(new String[]{ORDER_PRODUCT_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ORDER_PRODUCT_DESTINATION_PAGE, message, request, response)
                && CommandValidatorHelper.getInstance().isEmptyValidate(new String[]{ORDER_PRODUCT_ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, ORDER_PRODUCT_DESTINATION_PAGE, message, request, response));
    }
}

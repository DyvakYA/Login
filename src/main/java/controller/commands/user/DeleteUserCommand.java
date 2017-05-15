package controller.commands.user;

import controller.commands.Command;
import controller.commands.CommandHelper;
import controller.commands.validators.user.DeleteUserCommandValidator;
import model.extras.Localization;
import model.services.UserService;
import model.services.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.DELETE_USER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.REDIRECTED;
import static model.constants.UrlHolder.USER;

/**
 * @author Dyvak Yurii dyvakyurii@gmail.com
 */
public class DeleteUserCommand implements Command {

    private UserService userService= UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException{

        if (!new DeleteUserCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        userService.delete(Integer.valueOf(request.getParameter(USER_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
            .getLocalizedMessage(request, DELETE_USER_SUCCESSFUL_MSG));
        return CommandHelper.getInstance().roleCheckerSetAttributes(USER, request);
    }
}

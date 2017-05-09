package controller.commands.user;

import controller.commands.Command;
import controller.commands.validators.user.DeleteUserCommandValidator;
import model.extras.Localization;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.DELETE_USER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_USERS_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

public class DeleteUserCommand implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException{

        if (!new DeleteUserCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        userService.delete(Integer.valueOf(request.getParameter(USER_ID_ATTRIBUTE)));
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstance()
            .getLocalizedMessage(request, DELETE_USER_SUCCESSFUL_MSG));
        request.setAttribute(USERS_LIST_ATTRIBUTE, userService.getAll());
        return ADMIN_USERS_DESTINATION_PAGE;
    }
}

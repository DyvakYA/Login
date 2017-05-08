package controller.commands.user;

import controller.commands.Command;
import controller.commands.validators.user.CreateUserCommandValidator;
import model.entities.User;
import model.extras.Localization;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.constants.AttributesHolder.*;
import static model.constants.MsgHolder.CREATE_USER_SUCCESSFUL_MSG;
import static model.constants.UrlHolder.ADMIN_USERS_DESTINATION_PAGE;
import static model.constants.UrlHolder.REDIRECTED;

public class CreateUserCommand implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (!new CreateUserCommandValidator().validate(request, response)) {
            return REDIRECTED;
        }
        User user = new User.Builder()
                .setName(request.getParameter(USER_NAME_ATTRIBUTE))
                .setEmail(request.getParameter(USER_EMAIL_ATTRIBUTE))
                .setPasswordHash(request.getParameter(USER_PASSWORD_ATTRIBUTE))
                .setAdmin(Boolean.parseBoolean(request.getParameter(USER_ADMIN_ATTRIBUTE)))
                .setBlocked(Boolean.parseBoolean(request.getParameter(USER_BLOCKED_ATTRIBUTE)))
                .build();
        userService.create(user);
        request.setAttribute(RESULT_ATTRIBUTE, Localization.getInstanse()
                .getLocalizedMessage(request, CREATE_USER_SUCCESSFUL_MSG));
        request.setAttribute(USERS_LIST_ATTRIBUTE, userService.getAll());
        return ADMIN_USERS_DESTINATION_PAGE;
    }
}

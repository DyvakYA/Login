package controller.commands.user;

import controller.commands.Command;
import model.entities.User;
import model.services.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.constants.AttributesHolder.USERS_LIST_ATTRIBUTE;
import static model.constants.UrlHolder.ADMIN_USERS_DESTINATION_PAGE;

public class AdminGetAllUsersCommand implements Command {

    private UserService userService=UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> users=userService.getAll();
        request.setAttribute(USERS_LIST_ATTRIBUTE, users);
        return ADMIN_USERS_DESTINATION_PAGE;
    }

}

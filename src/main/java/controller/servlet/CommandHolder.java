package controller.servlet;

import controller.commands.Command;
import controller.commands.order.AdminGetProductsFromOrderCommand;
import controller.commands.order.AdminUpdateOrderCommand;
import controller.commands.order.DeleteOrderCommand;
import controller.commands.orderProduct.DeleteProductFromOrderCommand;
import controller.commands.product.*;
import controller.commands.user.*;
import controller.commands.userOrder.GetAllUserOrdersCommand;

import java.util.HashMap;
import java.util.Map;

import static model.constants.UrlHolder.*;

/**
 * This class is implementation of CommandHolder. It defines command for every supported request uri.
 *
 * @author dyvakyurii@gmail.com
 */
public class CommandHolder {

    public static final String DELIMITER = ":";
    private static final String GET = "GET" + DELIMITER;
    public static final String POST = "POST" + DELIMITER;

    /**
     * Holder for GET commands
     */
    private Map<String, Command> commands = new HashMap<>();

    CommandHolder() {
        init();
    }

    private void init() {

        commands.put(GET + LOCALE, new ChangeLocaleCommand());
        commands.put(GET + LOGIN, new LoginCommand());
        commands.put(GET + LOGOUT, new LogoutCommand());
        commands.put(GET + PRODUCTS, new GetAllProductsCommand());
        commands.put(GET + USER_PRODUCTS, new GetAllProductsCommand());
        commands.put(GET + ADMIN_PRODUCTS, new GetAllProductsCommand());
        commands.put(GET + ADMIN_DELETE_ORDER, new DeleteOrderCommand());
        commands.put(GET + USER_DELETE_ORDER, new DeleteOrderCommand());
        commands.put(GET + ADMIN_DELETE_PRODUCT_GET, new DeleteProductFromOrderCommand());
        commands.put(GET + FIND_PRODUCT_BY_PRICE, new FindProductsByPriceCommand());
        commands.put(GET + FIND_PRODUCT_BY_NAME, new FindProductsByNameCommand());
        commands.put(GET + USER_ORDERS, new GetAllUserOrdersCommand());
        commands.put(GET + ADMIN_ORDERS, new AdminGetProductsFromOrderCommand());
        commands.put(GET + ADMIN_GET_ALL_USERS, new AdminGetAllUsersCommand());
        commands.put(GET + USER_ADD_TO_ORDER, new UserAddProductToOrderCommand());
        commands.put(GET + ADMIN_UPDATE_ORDER, new AdminUpdateOrderCommand());
        commands.put(GET +  REGISTRATION, new RegisterUserCommand());

        commands.put(POST + ADMIN_GET_ALL_PRODUCTS_POST, new GetAllProductsCommand());
        commands.put(POST + ADMIN_GET_ALL_USERS_POST, new AdminGetAllUsersCommand());
        commands.put(POST + ADMIN_CREATE_USER, new CreateUserCommand());
        commands.put(POST + ADMIN_UPDATE_USER, new UpdateUserCommand());
        commands.put(POST + ADMIN_DELETE_USER, new DeleteUserCommand());
        commands.put(POST + ADMIN_CREATE_PRODUCT, new CreateProductCommand());
        commands.put(POST + ADMIN_UPDATE_PRODUCT, new UpdateProductCommand());
        commands.put(POST + ADMIN_DELETE_PRODUCT_POST, new DeleteProductCommand());

    }

    /**
     * @param commandKey Key of the command, mapped to certain uri and request method
     * @return Command instance, mapped to certain uri and request method
     */
    public Command findCommand(String commandKey) {
        return commands.getOrDefault(commandKey, (req, resp) -> INDEX);
    }
}

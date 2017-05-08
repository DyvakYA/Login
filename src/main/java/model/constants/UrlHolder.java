package model.constants;

/**
 * Created by User on 3/26/2017.
 */
public final class UrlHolder {

    public static final String LOGIN = "/shop/login";
    public static final String LOGOUT = "/shop/logout";
    public static final String PRODUCTS = "/shop/products";
    public static final String USER_PRODUCTS = "/shop/user/products";
    public static final String USER_ORDERS = "/shop/user/orders";
    public static final String ADMIN_PRODUCTS = "/shop/admin/products";
    public static final String ADMIN_ORDERS = "/shop/admin/orders";
    public static final String ADMIN_GET_ALL_USERS = "/shop/admin/adminGetAllUsers";
    public static final String ADMIN_GET_ALL_USERS_POST = "adminGetAllUsers";
    public static final String ADMIN_CREATE_USER = "createUser";
    public static final String ADMIN_UPDATE_USER = "updateUser";
    public static final String ADMIN_DELETE_USER = "deleteUser";
    public static final String ADMIN_GET_ALL_PRODUCTS = "adminGetAllProducts";
    public static final String ADMIN_CREATE_PRODUCT = "/createProduct";
    public static final String ADMIN_UPDATE_PRODUCT = "/updateProduct";
    public static final String ADMIN_DELETE_PRODUCT = "/deleteProduct";
    public static final String ADMIN_DELETE_ORDER = "/shop/admin/adminDeleteOrder";
    public static final String ADMIN_UPDATE_ORDER = "/shop/admin/adminUpdateOrder";
    public static final String USER_DELETE_ORDER = "/shop/user/userDeleteOrder";
    public static final String ADD_TO_ORDER = "/shop/addToOrder";
    public static final String USER_ADD_TO_ORDER = "/shop/addToOrder";

    public static final String LOCALE = "/shop/locale";
    public static final String REGISTRATION = "/shop/registration";

    public static final String INDEX = "/WEB-INF/index.jsp";
    public static final String REDIRECTED = "REDIRECTED";

    public static final String USER_ORDER_DESTINATION_PAGE= "/WEB-INF/user/order.jsp";
    public static final String ADMIN_USERS_DESTINATION_PAGE= "/WEB-INF/admin/users.jsp";
    public static final String ORDER_PRODUCT_DESTINATION_PAGE = "/orderProducts.jsp";
    public static final String ADMIN_PRODUCT_DESTINATION_PAGE = "/WEB-INF/admin/product.jsp";

    public static final String ADMIN_ORDER_DESTINATION_PAGE = "/WEB-INF/admin/order.jsp";

    public static final String ADMIN = "/WEB-INF/admin";
    public static final String USER = "/WEB-INF/user";
    public static final String GUEST = "/WEB-INF";
    public static final String PRODUCT = "/product.jsp";
    public static final String ORDER = "/order.jsp";




    private UrlHolder() {
    }
}
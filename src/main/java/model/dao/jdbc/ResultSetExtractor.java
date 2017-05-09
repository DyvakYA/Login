package model.dao.jdbc;

import model.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static model.constants.AttributesHolder.*;

/**
 * Created by User on 5/9/2017.
 */
public class ResultSetExtractor {

    private static class Holder {
        static final ResultSetExtractor INSTANCE = new ResultSetExtractor();
    }

    public static ResultSetExtractor getInstance() {
        return ResultSetExtractor.Holder.INSTANCE;
    }

    public OrderProduct getOrderProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new OrderProduct.Builder()
                .setId(resultSet.getInt(ORDER_PRODUCT_ID_ATTRIBUTE))
                .setOrderId(resultSet.getInt(ORDER_ID_ATTRIBUTE))
                .setProductId(resultSet.getInt(PRODUCT_ID_ATTRIBUTE))
                .setQuantity(resultSet.getInt(QUANTITY))
                .build();
    }

    public Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return new Order.Builder()
                .setOrderId(resultSet.getInt(ORDER_ID_ATTRIBUTE))
                .setOrderStatus(resultSet.getString(ORDER_STATUS_ATTRIBUTE))
                .setDate(resultSet.getDate(ORDER_DATE_ATTRIBUTE))
                .build();
    }

    public Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product.Builder()
                .setId(resultSet.getInt(PRODUCT_ID_ATTRIBUTE))
                .setName(resultSet.getString(PRODUCT_NAME_ATTRIBUTE))
                .setDescription(resultSet.getString(PRODUCT_DESCRIPTION_ATTRIBUTE))
                .setPrice(resultSet.getInt(PRODUCT_PRICE_ATTRIBUTE))
                .build();
    }

    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt(USER_ID_ATTRIBUTE))
                .setName(resultSet.getString(USER_NAME_ATTRIBUTE))
                .setEmail(resultSet.getString(USER_EMAIL_ATTRIBUTE))
                .setPasswordString(resultSet.getString(USER_AUTHENTICATE_ATTRIBUTE))
                .setAdmin(resultSet.getBoolean(USER_ADMIN_ATTRIBUTE))
                .setBlocked(resultSet.getBoolean(USER_BLOCKED_ATTRIBUTE))
                .build();
    }

    public UserOrder getUserOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserOrder.Builder()
                .setUserId(resultSet.getInt(USER_ID_ATTRIBUTE))
                .setOrderId(resultSet.getInt(ORDER_ID_ATTRIBUTE))
                .build();
    }
}
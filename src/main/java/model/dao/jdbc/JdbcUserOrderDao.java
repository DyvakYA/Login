package model.dao.jdbc;

import model.dao.UserOrderDao;
import model.dao.exception.DAOException;
import model.entities.Order;
import model.entities.UserOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.constants.AttributesHolder.*;

public class JdbcUserOrderDao implements UserOrderDao {

    private static final String SELECT_FROM_USER_ORDER_WHERE_USER_ID="SELECT * FROM user_orders WHERE user_id=?";
    private static final String SELECT_FROM_USER_ORDERS="SELECT * FROM user_orders";
    private static final String SELECT_ORDERS_FOR_USER_BY_ID="SELECT " +
            "Orders.order_id, Orders.order_status, Orders.order_date\n" +
            "FROM Orders\n" +
            "INNER JOIN user_orders ON orders.order_id=user_orders.order_id\n" +
            "WHERE user_id=?;";
    private static final String CREATE_USER_ORDER_QUERY="INSERT INTO user_orders (user_id , order_id)  " +
            "VALUES (?, ?)";
    private static final String UPDATE_USER_ORDERS_QUERY="UPDATE user_orders SET order_id=?" +
            "WHERE user_id=?";
    private static final String DELETE_USER_ORDERS_QUERY="DELETE FROM user_orders WHERE user_id=?";

    private static final String SQL_EXCEPTION="SQLException";

    private Connection connection;

    public JdbcUserOrderDao() {
    }

    JdbcUserOrderDao(Connection connection) {
        this.connection=connection;
    }

    public void setConnection(Connection connection) {
        this.connection=connection;
    }

    @Override
    public Optional<UserOrder> findById(int id) {
        Optional<UserOrder> routeStops=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_USER_ORDER_WHERE_USER_ID)) {
            query.setInt(1, id);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                routeStops=Optional.of(getUserOrderFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return routeStops;
    }

    @Override
    public List<UserOrder> findAll() {
        List<UserOrder> userOrders=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_USER_ORDERS)) {
            ResultSet result=query.executeQuery();
            while (result.next()) {
                userOrders.add(getUserOrderFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return userOrders;
    }

    @Override
    public List<Order> findAllOrdersForUser(int id) {
        List<Order> orders=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(SELECT_ORDERS_FOR_USER_BY_ID)) {
            query.setInt(1, id);
            ResultSet rs=query.executeQuery();
            while (rs.next()) {
                orders.add(new Order.Builder()
                        .setOrderId(rs.getInt(ORDER_ID_ATTRIBUTE))
                        .setOrderStatus(rs.getString(ORDER_STATUS_ATTRIBUTE))
                        .setDate(rs.getDate(ORDER_DATE_ATTRIBUTE))
                        .build());
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return orders;
    }


    @Override
    public void create(UserOrder userOrder) {
        try (PreparedStatement query=connection.prepareStatement(CREATE_USER_ORDER_QUERY)) {
            query.setInt(1, userOrder.getUserId());
            query.setInt(2, userOrder.getOrderId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(UserOrder userOrder, int id) {
        try (PreparedStatement query=connection.prepareStatement(UPDATE_USER_ORDERS_QUERY)) {
            query.setInt(1, userOrder.getOrderId());
            query.setInt(2, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement query=connection.prepareStatement(DELETE_USER_ORDERS_QUERY)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    private UserOrder getUserOrderFromResultSet(ResultSet rs) throws SQLException {
        UserOrder userOrder = new UserOrder.Builder()
                    .setUserId(rs.getInt(USER_ID_ATTRIBUTE))
                    .setOrderId(rs.getInt(ORDER_ID_ATTRIBUTE))
                    .build();
        return userOrder;
    }

}

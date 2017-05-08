package model.dao.jdbc;

import model.dao.OrderDao;
import model.dao.exception.DAOException;
import model.entities.Order;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.constants.AttributesHolder.*;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcOrderDao implements OrderDao {

    private static final String SELECT_FROM_ORDERS_WHERE_ORDER_ID="SELECT * FROM orders WHERE order_id=?";
    private static final String SELECT_FROM_ORDERS="SELECT * FROM orders";
    private static final String CREATE_ORDER_QUERY="INSERT INTO orders (order_status, order_date)  VALUES (?, ?)";
    private static final String UPDATE_ORDER_QUERY="UPDATE orders SET order_status=? WHERE order_id=?";
    private static final String DELETE_ORDER_QUERY="DELETE FROM orders WHERE order_id=?";

    private static final String SQL_EXCEPTION="SQLException";

    private Connection connection;

    public JdbcOrderDao() {
    }

    JdbcOrderDao(Connection connection) {
        this.connection=connection;
    }

    public void setConnection(Connection connection) {
        this.connection=connection;
    }

    @Override
    public Optional<Order> findById(int id) {
        Optional<Order> order=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_ORDERS_WHERE_ORDER_ID)) {
            query.setInt(1, id);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                order=Optional.of(getOrderFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_ORDERS)) {
            ResultSet result=query.executeQuery();
            while (result.next()) {
                orders.add(getOrderFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return orders;
    }

    @Override
    public void create(Order order) {
        try (PreparedStatement query=connection.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, order.getOrderStatus());
            query.setDate(2, java.sql.Date.valueOf(order.getDate()
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            query.executeUpdate();
            ResultSet resultSet = query.getGeneratedKeys();
            if (resultSet.next()){
                order.setOrderId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(Order order, int id) {
        try (PreparedStatement query=connection.prepareStatement(UPDATE_ORDER_QUERY)) {
            query.setString(1, order.getOrderStatus());
            query.setInt(2, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement query=connection.prepareStatement(DELETE_ORDER_QUERY)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order.Builder()
                    .setOrderId(resultSet.getInt(ORDER_ID_ATTRIBUTE))
                    .setOrderStatus(resultSet.getString(ORDER_STATUS_ATTRIBUTE))
                    .setDate(resultSet.getDate(ORDER_DATE_ATTRIBUTE))
                    .build();
        return order;
    }
}

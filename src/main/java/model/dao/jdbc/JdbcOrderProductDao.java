package model.dao.jdbc;

import model.dao.OrderProductDao;
import model.dao.exception.DAOException;
import model.entities.OrderProduct;
import model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.constants.AttributesHolder.*;

public class JdbcOrderProductDao implements OrderProductDao {

    private static final String SELECT_FROM_ORDER_PRODUCT_WHERE_ORDER_ID="SELECT * FROM order_products " +
            "WHERE order_id=?";
    private static final String SELECT_FROM_ORDER_PRODUCT="SELECT * FROM order_products";
    private static final String CREATE_ORDER_PRODUCT_QUERY="INSERT INTO order_products (order_id, product_id)  " +
            "VALUES (?, ?)";
    private static final String SELECT_FROM_ORDER_PRODUCT_WHERE_ORDER_ID_AND_PRODUCT_ID="SELECT * FROM order_products WHERE order_id=? AND product_id=?";
    private static final String UPDATE_ORDER_PRODUCT_QUERY="UPDATE order_products SET order_id=?, product_id=?, quantity=? " +
            "WHERE order_product_id=?";
    private static final String DELETE_ORDER_PRODUCT_QUERY="DELETE FROM order_products WHERE order_id=?";
    private static final String DELETE_PRODUCT_FROM_ORDER_QUERY="DELETE FROM order_products WHERE product_id=? AND order_id=?";

    private static final String SELECT_PRODUCTS_FROM_ORDER_BY_ID="SELECT order_products.order_id," +
            "products.product_id, products.product_name,products.product_description, products.product_price,order_products.quantity " +
            "FROM order_products INNER JOIN products " +
            "ON order_products.product_id=products.product_id " +
            "WHERE order_products.order_id=?";

    private static final String SQL_EXCEPTION="SQLException";

    private Connection connection;

    public JdbcOrderProductDao() {
    }

    JdbcOrderProductDao(Connection connection) {
        this.connection=connection;
    }

    public void setConnection(Connection connection) {
        this.connection=connection;
    }

    @Override
    public Optional<OrderProduct> findById(int id) {
        Optional<OrderProduct> orderProduct=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_ORDER_PRODUCT_WHERE_ORDER_ID)) {
            query.setInt(1, id);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                orderProduct=Optional.of(getOrderProductFromResultSet(result));
            }
            query.close();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return orderProduct;
    }

    @Override
    public List<OrderProduct> findAll() {
        List<OrderProduct> orderProducts=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_ORDER_PRODUCT)) {
            ResultSet result=query.executeQuery();
            while (result.next()) {
                orderProducts.add(getOrderProductFromResultSet(result));
            }
            query.close();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return orderProducts;
    }

    @Override
    public List<Product> findProductsByOrderId(int id) {
        List<Product> products=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(SELECT_PRODUCTS_FROM_ORDER_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet=query.executeQuery();
            while (resultSet.next()) {
                products.add(new Product.Builder()
                        .setId(resultSet.getInt(PRODUCT_ID_ATTRIBUTE))
                        .setName(resultSet.getString(PRODUCT_NAME_ATTRIBUTE))
                        .setDescription(resultSet.getString(PRODUCT_DESCRIPTION_ATTRIBUTE))
                        .setPrice(resultSet.getInt(PRODUCT_PRICE_ATTRIBUTE))
                        .build());
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return products;
    }

    @Override
    public void deleteProductFromOrder(int orderId, int productId) {
        try (PreparedStatement query=connection.prepareStatement(DELETE_PRODUCT_FROM_ORDER_QUERY)) {
            query.setInt(1, productId);
            query.setInt(2, orderId);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public Optional<OrderProduct> findOrderProductByOrderIdAndProductId(int orderId, int productId) {
        Optional<OrderProduct> orderProduct=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_ORDER_PRODUCT_WHERE_ORDER_ID_AND_PRODUCT_ID)) {
            query.setInt(1, orderId);
            query.setInt(2, productId);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                orderProduct=Optional.of(getOrderProductFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return orderProduct;
    }

    @Override
    public void create(OrderProduct orderProduct) {
        try (PreparedStatement query=connection.prepareStatement(CREATE_ORDER_PRODUCT_QUERY)) {
            query.setInt(1, orderProduct.getOrderId());
            query.setInt(2, orderProduct.getProductId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(OrderProduct orderProduct, int id) {
        try (PreparedStatement query=connection.prepareStatement(UPDATE_ORDER_PRODUCT_QUERY)) {
            query.setInt(1, orderProduct.getOrderId());
            query.setInt(2, orderProduct.getProductId());
            query.setInt(3, orderProduct.getQuantity());
            query.setInt(4, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement query=connection.prepareStatement(DELETE_ORDER_PRODUCT_QUERY)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    private OrderProduct getOrderProductFromResultSet(ResultSet resultSet) throws SQLException {
        OrderProduct orderProduct=new OrderProduct.Builder()
                .setId(resultSet.getInt(ORDER_PRODUCT_ID_ATTRIBUTE))
                .setOrderId(resultSet.getInt(ORDER_ID_ATTRIBUTE))
                .setProductId(resultSet.getInt(PRODUCT_ID_ATTRIBUTE))
                .setQuantity(resultSet.getInt(QUANTITY))
                .build();
        return orderProduct;
    }
}

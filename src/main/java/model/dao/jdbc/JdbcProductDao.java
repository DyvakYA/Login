package model.dao.jdbc;

import model.dao.ProductDao;
import model.dao.exception.DAOException;
import model.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.constants.AttributesHolder.*;

public class JdbcProductDao implements ProductDao {

    private static final String SELECT_FROM_PRODUCTS_WHERE_PRODUCT_ID="SELECT * FROM products WHERE product_id=?";
    private static final String SELECT_FROM_PRODUCTS_BY_PRICE="SELECT * FROM products\n" +
            "WHERE product_price BETWEEN ? AND ?";
    private static final String SELECT_FROM_PRODUCTS_BY_NAME="SELECT * FROM products WHERE product_name LIKE ?";

    private static final String SELECT_FROM_PRODUCTS="SELECT * FROM products";
    private static final String CREATE_PRODUCT_QUERY="INSERT " +
            "INTO products (product_name, product_description, product_price)  VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUERY="UPDATE products " +
            "SET product_name=?, product_description=?, product_price=? WHERE product_id=?";
    private static final String DELETE_PRODUCT_QUERY="DELETE FROM products WHERE product_id=?";


    private static final String SQL_EXCEPTION="SQLException";


    private Connection connection;

    public JdbcProductDao() {
    }

    JdbcProductDao(Connection connection) {
        this.connection=connection;
    }

    public void setConnection(Connection connection) {
        this.connection=connection;
    }

    @Override
    public Optional<Product> findById(int id) {
        Optional<Product> product=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_PRODUCTS_WHERE_PRODUCT_ID)) {
            query.setInt(1, id);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                product=Optional.of(getProductFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_PRODUCTS)) {
            ResultSet result=query.executeQuery();
            while (result.next()) {
                products.add(getProductFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByPrice(int first, int second) {
        List<Product> products=new ArrayList<>();
        try {
            PreparedStatement query=connection.prepareStatement(SELECT_FROM_PRODUCTS_BY_PRICE);
            query.setString(1, String.valueOf(first));
            query.setString(2, String.valueOf(second));
            ResultSet result=query.executeQuery();
            while (result.next()) {
                products.add(getProductFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByName(String name) {
        List<Product> products=new ArrayList<>();
        try {
            PreparedStatement query=connection.prepareStatement(SELECT_FROM_PRODUCTS_BY_NAME);
            query.setString(1, "%" + name + "%");
            ResultSet result=query.executeQuery();
            while (result.next()) {
                products.add(getProductFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return products;
    }

    @Override
    public void create(Product product) {
        try (PreparedStatement query=connection.prepareStatement(CREATE_PRODUCT_QUERY)) {
            query.setString(1, product.getName());
            query.setString(2, product.getDescription());
            query.setLong(3, product.getPrice());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(Product product, int id) {
        try (PreparedStatement query=connection.prepareStatement(UPDATE_PRODUCT_QUERY)) {
            query.setString(1, product.getName());
            query.setString(2, product.getDescription());
            query.setLong(3, product.getPrice());
            query.setInt(4, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement query=connection.prepareStatement(DELETE_PRODUCT_QUERY)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product=new Product.Builder()
                .setId(resultSet.getInt(PRODUCT_ID_ATTRIBUTE))
                .setName(resultSet.getString(PRODUCT_NAME_ATTRIBUTE))
                .setDescription(resultSet.getString(PRODUCT_DESCRIPTION_ATTRIBUTE))
                .setPrice(resultSet.getInt(PRODUCT_PRICE_ATTRIBUTE))
                .build();
        return product;
    }
}

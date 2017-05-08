package model.dao.jdbc;

import model.dao.*;
import model.dao.exception.DAOException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;
    private static final String DB_URL = "url";

    private DataSource dataSource;

    public JdbcDaoFactory() {

        try {
//            InputStream inputStream =
//                    DaoFactory.class.getResourceAsStream(DB_FILE);
//            Properties dbProps = new Properties();
//            dbProps.load(inputStream);
//            String url = dbProps.getProperty(DB_URL);
//            connection = DriverManager.getConnection(url, dbProps);

            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/mydb");

        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public DaoConnection getConnectionForTesting() {
        return new JdbcDaoConnection(connection);
    }

    @Override
    public OrderDao createOrderDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcOrderDao(sqlConnection);
    }

    @Override
    public ProductDao createProductDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcProductDao(sqlConnection);
    }

    @Override
    public OrderProductDao createOrderProductDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcOrderProductDao(sqlConnection);
    }

    @Override
    public UserOrderDao createUserOrderDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserOrderDao(sqlConnection);
    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserDao(sqlConnection);
    }

}

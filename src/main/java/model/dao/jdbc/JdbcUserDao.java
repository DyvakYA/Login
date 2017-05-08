package model.dao.jdbc;

import model.dao.UserDao;
import model.dao.exception.DAOException;
import model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.constants.AttributesHolder.*;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcUserDao implements UserDao {

    private static final String SELECT_FROM_USERS_WHERE_USER_ID="SELECT * FROM users WHERE user_id=?";
    private static final String SELECT_FROM_USERS="SELECT * FROM users";
    private static final String SELECT_USER_BY_EMAIL="SELECT * FROM users WHERE lower(email) = ?";
    private static final String SELECT_FROM_USERS_WHERE_NAME="SELECT * FROM users WHERE user_name=?";
    private static final String CREATE_USER_QUERY="INSERT INTO users (user_name, email, password, isAdmin ,isBlocked)  " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY="UPDATE users " +
            "SET user_name=?, email=?, password=?, isAdmin=?, isBlocked=? WHERE user_id=?";
    private static final String DELETE_USER_QUERY="DELETE FROM users WHERE user_id=?";

    private static final String SQL_EXCEPTION="SQLException";

    private Connection connection;

    public JdbcUserDao() {
    }

    JdbcUserDao(Connection connection) {
        this.connection=connection;
    }

    public void setConnection(Connection connection) {
        this.connection=connection;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> user=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_USERS_WHERE_USER_ID)) {
            query.setInt(1, id);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                user=Optional.of(getUserFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return user;
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> user=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME)) {
            query.setString(1, name);
            ResultSet result=query.executeQuery();
            if (result.next()) {
                user=Optional.of(getUserFromResultSet(result));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user=Optional.empty();
        try (PreparedStatement query=connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            query.setString(1, email.toLowerCase());
            ResultSet result=query.executeQuery();
            if (result.next()) {
                user=Optional.of(getUserFromResultSet(result));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users=new ArrayList<>();
        try {
            PreparedStatement query=connection.prepareStatement(SELECT_FROM_USERS);
            ResultSet result=query.executeQuery();
            while (result.next()) {
                users.add(getUserFromResultSet(result));
            }
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
        return users;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement query=connection.prepareStatement(CREATE_USER_QUERY)) {
            query.setString(1, user.getName());
            query.setString(2, user.getEmail());
            query.setString(3, user.getPasswordHash());
            query.setBoolean(4, user.isAdmin());
            query.setBoolean(5, user.isBlocked());
            query.executeUpdate();
            query.close();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    @Override
    public void update(User user, int id) {
        try (PreparedStatement query=connection.prepareStatement(UPDATE_USER_QUERY)) {
            query.setString(1, user.getName());
            query.setString(2, user.getEmail());
            query.setString(3, String.valueOf(user.getPasswordHash()));
            query.setBoolean(4, user.isAdmin());
            query.setBoolean(5, user.isBlocked());
            query.setString(6, String.valueOf(id));
            query.executeUpdate();
            query.close();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement query=connection.prepareStatement(DELETE_USER_QUERY)) {
            query.setInt(1, id);
            query.executeUpdate();
            query.close();
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User.Builder()
                    .setId(resultSet.getInt(USER_ID_ATTRIBUTE))
                    .setName(resultSet.getString(USER_NAME_ATTRIBUTE))
                    .setEmail(resultSet.getString(USER_EMAIL_ATTRIBUTE))
                    .setPasswordString(resultSet.getString(USER_PASSWORD_ATTRIBUTE))
                    .setAdmin(resultSet.getBoolean(USER_ADMIN_ATTRIBUTE))
                    .setBlocked(resultSet.getBoolean(USER_BLOCKED_ATTRIBUTE))
                    .build();
        return user;
    }
}

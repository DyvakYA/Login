package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.services.UserServiceable;

import java.util.List;
import java.util.Optional;

public class UserService implements UserServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory=daoFactory;
    }

    private static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public Optional<User> login(String email, String password) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        return userDao.getUserByEmail(email)
                .filter(user -> (user.calcPasswordHash(password))
                        .equals(user.getPasswordHash()));
    }


    public List<User> getAll() {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        return userDao.findAll();
    }


    public Optional<User> getByEmail(String email) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        return userDao.getUserByEmail(email);
    }

    public Optional<User> getById(int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        return userDao.findById(id);
    }

    public void create(User user) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        userDao.create(user);
        connection.commitTransaction();
    }

    public void update(User user, int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        userDao.update(user, id);
        connection.commitTransaction();
    }

    public void delete(int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserDao userDao = daoFactory.createUserDao(connection);
        userDao.delete(id);
        connection.commitTransaction();
    }

}

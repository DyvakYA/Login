package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserOrderDao;
import model.entities.Order;
import model.entities.UserOrder;
import model.services.UserOrderServiceable;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class UserOrderService implements UserOrderServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Order> getOrdersForUser(int user) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserOrderDao userOrderDao = daoFactory.createUserOrderDao(connection);
        return userOrderDao.findAllOrdersForUser(user);
    }

    private static class Holder {
        static final UserOrderService INSTANCE = new UserOrderService();
    }

    public static UserOrderService getInstance() {
        return Holder.INSTANCE;
    }

    public List<UserOrder> getAll() {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserOrderDao userOrderDao = daoFactory.createUserOrderDao(connection);
        return userOrderDao.findAll();
    }

    public void create(UserOrder userOrder) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserOrderDao userOrderDao = daoFactory.createUserOrderDao(connection);
        userOrderDao.create(userOrder);
        connection.commitTransaction();
    }

    public void update(UserOrder userOrder, int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserOrderDao userOrderDao = daoFactory.createUserOrderDao(connection);
        userOrderDao.update(userOrder, id);
        connection.commitTransaction();
    }

    public void delete(int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        UserOrderDao transportDao = daoFactory.createUserOrderDao(connection);
        transportDao.delete(id);
        connection.commitTransaction();
    }

}

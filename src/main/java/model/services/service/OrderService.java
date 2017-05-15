package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.OrderDao;
import model.entities.Order;
import model.services.OrderServiceable;

import java.util.Date;
import java.util.List;

import static model.constants.AttributesHolder.STARTED;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class OrderService implements OrderServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();



    private static class Holder {
        static final OrderService INSTANCE = new OrderService();
    }

    public static OrderService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Order> getAll() {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderDao orderDao=daoFactory.createOrderDao(connection);
            return orderDao.findAll();
        }
    }

    public Order createDefaultOrder() {
        Order order;
        try (DaoConnection connection=daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderDao orderDao=daoFactory.createOrderDao(connection);
            order=new Order.Builder()
                    .setOrderStatus(STARTED)
                    .setDate(new Date())
                    .build();
            orderDao.create(order);
            connection.commitTransaction();
        }
        return order;
    }

    public void create(Order order) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderDao orderDao=daoFactory.createOrderDao(connection);
            orderDao.create(order);
            connection.commitTransaction();
        }
    }

    public void update(Order order, int id) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderDao orderDao=daoFactory.createOrderDao(connection);
            orderDao.update(order, id);
            connection.commitTransaction();
        }
    }

    public void delete(int id) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderDao orderDao=daoFactory.createOrderDao(connection);
            orderDao.delete(id);
            connection.commitTransaction();
        }
    }
}

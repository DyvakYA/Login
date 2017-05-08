package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.OrderProductDao;
import model.entities.OrderProduct;
import model.entities.Product;
import model.services.OrderProductServiceable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class OrderProductService implements OrderProductServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final OrderProductService INSTANCE = new OrderProductService();
    }

    public static OrderProductService getInstance() {
        return Holder.INSTANCE;
    }

    public List<OrderProduct> getAll() {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        return orderProductDao.findAll();
    }

    public void create(OrderProduct orderProduct) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        orderProductDao.create(orderProduct);
        connection.commitTransaction();
    }

    public void update(OrderProduct orderProduct, int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        orderProductDao.update(orderProduct, id);
        connection.commitTransaction();
    }

    public void delete(int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        orderProductDao.delete(id);
        connection.commitTransaction();
    }

    public Optional<OrderProduct> findById(int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        return orderProductDao.findById(id);
    }

    public List<Product> getAllProductsOnOrder(int orderId) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        return orderProductDao.findProductsByOrderId(orderId);

    }

    public Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        return orderProductDao.findOrderProductByOrderIdAndProductId(orderId, productId);
    }

    public void deleteProductFromOrder(int orderId, int productId) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        OrderProductDao orderProductDao = daoFactory.createOrderProductDao(connection);
        orderProductDao.deleteProductFromOrder(orderId, productId);
        connection.commitTransaction();
    }
}

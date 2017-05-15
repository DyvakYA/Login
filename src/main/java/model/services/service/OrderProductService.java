package model.services.service;

import model.dao.*;
import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;
import model.entities.UserOrder;
import model.services.OrderProductServiceable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            return orderProductDao.findAll();
        }
    }

    public void createUserOrderAndOrderProduct(int userId, int orderId, int productId, int quantity) {
        try (DaoConnection connection=daoFactory.getConnection()) {
            connection.beginTransaction();
            UserOrderDao userOrderDao=daoFactory.createUserOrderDao(connection);
            UserOrder userOrder = new UserOrder.Builder()
                    .setUserId(userId)
                    .setOrderId(orderId)
                    .build();
            userOrderDao.create(userOrder);
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            OrderProduct orderProduct=new OrderProduct.Builder()
                    .setOrderId(orderId)
                    .setProductId(productId)
                    .setQuantity(quantity)
                    .build();
            orderProduct.setProductSum((long)orderProduct.getQuantity() *
                    orderProductDao.getProductPrice(orderProduct));
            orderProductDao.create(orderProduct);
            setOrderTotalPrice(orderProduct, connection, orderProductDao);
            connection.commitTransaction();
        }
    }

    public void create(OrderProduct orderProduct) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            orderProduct.setProductSum((long)orderProduct.getQuantity() *
                    orderProductDao.getProductPrice(orderProduct));
            orderProductDao.create(orderProduct);
            setOrderTotalPrice(orderProduct, connection, orderProductDao);
            connection.commitTransaction();
        }
    }

    public void increaseQuantityWhenAddProduct(OrderProduct orderProduct, int quantity) {
        try (DaoConnection connection=daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
            orderProduct.setProductSum((long)orderProduct.getQuantity() * orderProductDao.getProductPrice(orderProduct));
            orderProductDao.update(orderProduct, orderProduct.getId());
            setOrderTotalPrice(orderProduct, connection, orderProductDao);
            connection.commitTransaction();
        }
    }

    public void update(OrderProduct orderProduct, int id) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            orderProduct.setProductSum((long)orderProduct.getQuantity() * orderProductDao.getProductPrice(orderProduct));
            orderProductDao.update(orderProduct, id);
            setOrderTotalPrice(orderProduct, connection, orderProductDao);
            connection.commitTransaction();
        }
    }

    private void setOrderTotalPrice(OrderProduct orderProduct, DaoConnection connection, OrderProductDao orderProductDao) {
        Optional<Order> optionalOrder = orderProductDao.findOrderByOrderProductId(orderProduct.getId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setTotalPrice(orderProductDao.getOrderTotalPrice(order));
            OrderDao orderDao=daoFactory.createOrderDao(connection);
            orderDao.update(order, order.getId());
        }

    }

    public void delete(int id) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            orderProductDao.delete(id);
            connection.commitTransaction();
        }
    }

    public Optional<OrderProduct> findById(int id) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            return orderProductDao.findById(id);
        }
    }

    public Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId) {
        try (DaoConnection connection=daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            return orderProductDao.findOrderProductByOrderIdAndProductId(orderId, productId);
        }
    }

    public void deleteProductFromOrder(int orderId, int productId) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            orderProductDao.deleteProductFromOrder(orderId, productId);
            connection.commitTransaction();
        }
    }

    public Map<Order, Map<OrderProduct, Product>> getOrdersMap(List<Order> orders) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            connection.beginTransaction();
            OrderProductDao orderProductDao=daoFactory.createOrderProductDao(connection);
            Map<Order, Map<OrderProduct, Product>> orderMap = new HashMap<>();
            for (Order order : orders) {
                List<OrderProduct> orderProductList = orderProductDao.findOrderProductsByOrderId(order.getId());
                Map<OrderProduct, Product> orderProductMap = new HashMap<>();
                for (OrderProduct orderProduct : orderProductList) {
                    Optional<Product> optionalProduct =  orderProductDao.findProductByOrderProductId(orderProduct.getId());
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        orderProductMap.put(orderProduct, product);
                    }

                }
                orderMap.put(order, orderProductMap);
            }
            return orderMap;
        }
    }
}

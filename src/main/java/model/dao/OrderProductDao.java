package model.dao;

import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;

import java.util.List;
import java.util.Optional;

public interface OrderProductDao extends GenericDao<OrderProduct> {

    Optional<OrderProduct> findById(int id);

    List<OrderProduct> findAll();

    void deleteProductFromOrder(int orderId, int productId);

    Optional<OrderProduct> findOrderProductByOrderIdAndProductId(int orderId, int productId);

    List<OrderProduct> findOrderProductsByOrderId(int orderId);

    Optional<Product> findProductByOrderProductId(int orderProductId);

    int getProductPrice(OrderProduct orderProduct);

    long getOrderTotalPrice(Order order);

    Optional<Order> findOrderByOrderProductId(int id);
}



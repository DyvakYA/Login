package model.services;

import model.entities.Order;
import model.entities.OrderProduct;
import model.entities.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface OrderProductService {

    List<OrderProduct> getAll();

    void create(OrderProduct orderProduct);

    void update(OrderProduct orderProduct, int id);

    void delete(int id);

    Optional<OrderProduct> findById(int id);

    Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId);

    void deleteProductFromOrder(int orderId, int productId);

    Map<Order,Map<OrderProduct,Product>> getOrdersMap(List<Order> orderList);

    void createUserOrderAndOrderProduct(int userId, int orderId, int productId, int quantity);

    void increaseQuantityWhenAddProduct(OrderProduct orderProduct, int quantity);
}

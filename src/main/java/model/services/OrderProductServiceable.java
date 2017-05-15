package model.services;

import model.entities.OrderProduct;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface OrderProductServiceable {

    List<OrderProduct> getAll();

    void create(OrderProduct orderProduct);

    void update(OrderProduct orderProduct, int id);

    void delete(int id);

    Optional<OrderProduct> findById(int id);

    Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId);

    void deleteProductFromOrder(int orderId, int productId);
}

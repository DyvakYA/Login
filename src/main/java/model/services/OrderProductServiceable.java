package model.services;

import model.entities.OrderProduct;
import model.entities.Product;

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

    List<Product> getAllProductsOnOrder(int orderId);

    Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId);

    void deleteProductFromOrder(int orderId, int productId);
}

package model.dao;

import model.entities.OrderProduct;
import model.entities.Product;

import java.util.List;
import java.util.Optional;

public interface OrderProductDao extends GenericDao<OrderProduct> {

    Optional<OrderProduct> findById(int id);

    List<OrderProduct> findAll();

    List<Product> findProductsByOrderId(int orderId);

    void deleteProductFromOrder(int orderId, int productId);

    Optional<OrderProduct> findOrderProductByOrderIdAndProductId(int orderId, int productId);


}


package model.services;

import model.entities.Order;

import java.util.List;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface OrderService {

    List<Order> getAll();

    void create(Order order);

    void update(Order order, int id);

    void delete(int id);

    Order createDefaultOrder();

    void updateOrderStatus(Order order, int integer);
}


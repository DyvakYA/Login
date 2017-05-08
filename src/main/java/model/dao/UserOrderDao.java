package model.dao;

import model.entities.Order;
import model.entities.UserOrder;

import java.util.List;
import java.util.Optional;

public interface UserOrderDao extends GenericDao<UserOrder> {

    Optional<UserOrder> findById(int id);

    List<UserOrder> findAll();

    List<Order> findAllOrdersForUser(int user);
}


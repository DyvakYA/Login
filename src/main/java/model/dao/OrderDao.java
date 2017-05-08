package model.dao;

import model.entities.Order;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface OrderDao extends GenericDao<Order> {

    Optional<Order> findById(int id);

    List<Order> findAll();
}
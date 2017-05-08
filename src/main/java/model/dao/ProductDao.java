package model.dao;

import model.entities.Product;

import java.util.List;
import java.util.Optional;


public interface ProductDao extends GenericDao<Product> {

    Optional<Product> findById(int id);

    List<Product> findAll();

    List<Product> findProductsByPrice(int first, int second);

    List<Product> findProductsByName(String name);
}

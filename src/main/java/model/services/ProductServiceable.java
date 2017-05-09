package model.services;

import model.entities.Product;

import java.util.List;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface ProductServiceable {

    List<Product> getAll();

    void create(Product product);

    void update(Product product, int id);

    void delete(int id);

    List<Product> getProductsByPrice(int first, int second);

    List<Product> getProductsByName(String name);

}

package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;
import model.services.ProductServiceable;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class ProductService implements ProductServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final ProductService INSTANCE = new ProductService();
    }

    public static ProductService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Product> getAll() {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        ProductDao productDao = daoFactory.createProductDao(connection);
        List<Product> as = productDao.findAll();
        connection.close();
        return as;
    }

    public void create(Product product) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        ProductDao productDao = daoFactory.createProductDao(connection);
        productDao.create(product);
        connection.commitTransaction();
    }

    public void update(Product product, int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        ProductDao productDao = daoFactory.createProductDao(connection);
        productDao.update(product, id);
        connection.commitTransaction();
    }

    public void delete(int id) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        ProductDao productDao = daoFactory.createProductDao(connection);
        productDao.delete(id);
        connection.commitTransaction();
    }

    public List<Product> getProductsByPrice(int first, int second) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        ProductDao productDao = daoFactory.createProductDao(connection);
        return productDao.findProductsByPrice(first, second);
    }

    public List<Product> getProductsByName(String name) {
        DaoConnection connection = daoFactory.getConnection();
        connection.beginTransaction();
        ProductDao productDao = daoFactory.createProductDao(connection);
        return productDao.findProductsByName(name);
    }
}

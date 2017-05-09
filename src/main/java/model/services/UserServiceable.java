package model.services;

import model.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface UserServiceable {

    Optional<User> login(String email, String password) ;

    List<User> getAll();

    Optional<User> getByEmail(String email);

    Optional<User> getById(int id);

    void create(User user);

    void update(User user, int id);

    void delete(int id);

}

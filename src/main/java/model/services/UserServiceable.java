package model.services;

import model.entities.User;

import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface UserServiceable {
    Optional<User> login(String email, String password) ;
}

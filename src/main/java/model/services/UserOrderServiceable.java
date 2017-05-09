package model.services;

import model.entities.UserOrder;

import java.util.List;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface UserOrderServiceable {

    List<UserOrder> getAll();

    void create(UserOrder userOrder);

    void update(UserOrder userOrder, int id);

    void delete(int id);

}

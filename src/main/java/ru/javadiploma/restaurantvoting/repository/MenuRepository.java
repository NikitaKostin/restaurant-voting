package ru.javadiploma.restaurantvoting.repository;

import ru.javadiploma.restaurantvoting.model.Menu;

import java.util.List;

public interface MenuRepository {
    // null if updated restaurant menu does not belong to restaurantId
    Menu save(Menu menu, int dishId, int restaurantId);

    // null if restaurant menu does not belong to restaurantId
    Menu get(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);
}

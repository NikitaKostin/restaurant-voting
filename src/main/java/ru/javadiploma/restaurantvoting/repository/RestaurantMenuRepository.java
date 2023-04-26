package ru.javadiploma.restaurantvoting.repository;

import ru.javadiploma.restaurantvoting.model.RestaurantMenu;

import java.util.List;

public interface RestaurantMenuRepository {
    // null if updated restaurant menu does not belong to restaurantId
    RestaurantMenu save(RestaurantMenu restaurantMenu, int restaurantId);

    // null if restaurant menu does not belong to restaurantId
    RestaurantMenu get(int id, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);
}

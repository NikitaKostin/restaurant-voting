package repository;

import model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}
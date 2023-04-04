package repository;

import model.Menu;

import java.util.List;

public interface MenuRepository {
    // null if updated menu does not belong to restaurantId
    Menu save(Menu menu, int restaurantId);

    // null if menu does not belong to restaurantId
    Menu get(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);

    List<Menu> getAllForToday(int restaurantId);
}

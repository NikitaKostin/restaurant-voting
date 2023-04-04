package repository;

import model.Dish;

import java.util.List;

public interface DishRepository {
    // null if updated dish does not belong to menuId
    Dish save(Dish dish, int menuId);

    // null if dish does not belong to menuId
    Dish get(int id, int menuId);

    List<Dish> getAll(int menuId);

    List<Dish> getAllForToday(int menuId);
}

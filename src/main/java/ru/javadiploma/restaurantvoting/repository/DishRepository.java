package ru.javadiploma.restaurantvoting.repository;

import ru.javadiploma.restaurantvoting.model.Dish;

import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish);

    // null if not found
    Dish get(int id);

    // null if not found
    List<Dish> getAll();
}

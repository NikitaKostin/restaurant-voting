package ru.javadiploma.restaurantvoting.util;

import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.to.DishTo;

public class DishUtil {

    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getName(), dishTo.getPrice());
    }

    public static Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }
}

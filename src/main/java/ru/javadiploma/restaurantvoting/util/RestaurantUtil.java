package ru.javadiploma.restaurantvoting.util;

import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;

public class RestaurantUtil {
    public static Restaurant createNewFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName());
    }
}

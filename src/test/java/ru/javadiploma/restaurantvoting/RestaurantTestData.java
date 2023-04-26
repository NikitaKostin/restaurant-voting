package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.Restaurant;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class);

    public static final int RESTAURANT_1_ID = 1;
    public static final int RESTAURANT_2_ID = 2;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "Чебуречная");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_1_ID, "Пельменная");

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_1_ID, "Обновленное название");
    }
}

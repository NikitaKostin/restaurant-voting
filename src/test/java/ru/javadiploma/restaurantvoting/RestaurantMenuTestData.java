package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.RestaurantMenu;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class RestaurantMenuTestData {
    public static final MatcherFactory.Matcher<RestaurantMenu> RESTAURANT_MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantMenu.class, "restaurant");

    public static final int RESTAURANT_MENU_1_ID = 1;
    public static final int RESTAURANT_MENU_2_ID = 2;
    public static final int RESTAURANT_MENU_3_ID = 3;
    public static final int RESTAURANT_MENU_4_ID = 4;
    public static final int RESTAURANT_MENU_8_ID = 8;
    public static final int NOT_FOUND = -1;

    public static final RestaurantMenu restaurantMenu1 = new RestaurantMenu(RESTAURANT_MENU_1_ID, "Burger", 35, LocalDate.of(2020, Month.APRIL, 4));
    public static final RestaurantMenu restaurantMenu2 = new RestaurantMenu(RESTAURANT_MENU_2_ID, "Cheese Burger", 40, LocalDate.of(2020, Month.APRIL, 5));
    public static final RestaurantMenu restaurantMenu3 = new RestaurantMenu(RESTAURANT_MENU_3_ID, "Cola", 8, LocalDate.of(2020, Month.APRIL, 4));
    public static final RestaurantMenu restaurantMenu4 = new RestaurantMenu(RESTAURANT_MENU_4_ID, "Tea", 7, LocalDate.of(2020, Month.APRIL, 5));
    public static final RestaurantMenu restaurantMenu8Now = new RestaurantMenu(RESTAURANT_MENU_8_ID, "Dish of the day", 30, LocalDate.now());

    public static final List<RestaurantMenu> firstRestaurantMenu = List.of(restaurantMenu1, restaurantMenu2, restaurantMenu3, restaurantMenu4, restaurantMenu8Now);

    public static RestaurantMenu getNew() {
        return new RestaurantMenu(null, "New dish", 30, LocalDate.now());
    }

    public static RestaurantMenu getUpdated() {
        return new RestaurantMenu(RESTAURANT_MENU_3_ID, "Cherry cola", 8, LocalDate.of(2020, Month.APRIL, 4));
    }

}

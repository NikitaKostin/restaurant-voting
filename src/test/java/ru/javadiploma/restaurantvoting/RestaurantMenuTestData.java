package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.RestaurantMenu;

import java.time.LocalDate;
import java.time.Month;

public class RestaurantMenuTestData {
    public static final MatcherFactory.Matcher<RestaurantMenu> RESTAURANT_MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantMenu.class, "restaurant");

    public static final int RESTAURANT_MENU_1_ID = 1;
    public static final int RESTAURANT_MENU_2_ID = 2;
    public static final int RESTAURANT_MENU_3_ID = 3;
    public static final int RESTAURANT_MENU_4_ID = 4;
    public static final int RESTAURANT_MENU_8_ID = 8;
    public static final int NOT_FOUND = -1;

    public static final RestaurantMenu restaurantMenu1 = new RestaurantMenu(RESTAURANT_MENU_1_ID, "Чебурек с мясом", 130, LocalDate.of(2020, Month.APRIL, 4));
    public static final RestaurantMenu restaurantMenu2 = new RestaurantMenu(RESTAURANT_MENU_2_ID, "Чебурек с вишней", 105, LocalDate.of(2020, Month.APRIL, 5));
    public static final RestaurantMenu restaurantMenu3 = new RestaurantMenu(RESTAURANT_MENU_3_ID, "Компот", 40, LocalDate.of(2020, Month.APRIL, 4));
    public static final RestaurantMenu restaurantMenu4 = new RestaurantMenu(RESTAURANT_MENU_4_ID, "Чай", 40, LocalDate.of(2020, Month.APRIL, 5));
    public static final RestaurantMenu restaurantMenu8Now = new RestaurantMenu(RESTAURANT_MENU_8_ID, "Блюдо дня", 100, LocalDate.now());

    public static RestaurantMenu getNew() {
        return new RestaurantMenu(null, "Новое блюдо", 102, LocalDate.now());
    }

    public static RestaurantMenu getUpdated() {
        return new RestaurantMenu(RESTAURANT_MENU_3_ID, "Компот обновленный", 41, LocalDate.of(2020, Month.APRIL, 4));
    }

}

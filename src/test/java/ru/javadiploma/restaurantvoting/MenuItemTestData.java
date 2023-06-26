package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;
import ru.javadiploma.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static ru.javadiploma.restaurantvoting.DishTestData.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.restaurant1;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.restaurant2;

public class MenuItemTestData {
    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "dish", "restaurant", "createDate");

    public static final int MENU_ITEM_1_ID = 1;
    public static final int MENU_ITEM_2_ID = 2;
    public static final int MENU_ITEM_3_ID = 3;
    public static final int MENU_ITEM_4_ID = 4;
    public static final int MENU_ITEM_5_ID = 4;
    public static final int MENU_ITEM_6_ID = 4;
    public static final int MENU_ITEM_7_ID = 4;
    public static final int MENU_ITEM_8_ID = 8;
    public static final int MENU_ITEM_9_ID = 9;

    public static final MenuItem MENU_ITEM_1 = new MenuItem(MENU_ITEM_1_ID, dish1, restaurant1, LocalDate.of(2023, Month.APRIL, 4));
    public static final MenuItem MENU_ITEM_2 = new MenuItem(MENU_ITEM_2_ID, dish2, restaurant1, LocalDate.of(2023, Month.APRIL, 5));
    public static final MenuItem MENU_ITEM_3 = new MenuItem(MENU_ITEM_3_ID,  dish3, restaurant1, LocalDate.of(2023, Month.APRIL, 4));
    public static final MenuItem MENU_ITEM_4 = new MenuItem(MENU_ITEM_4_ID, dish4, restaurant1, LocalDate.of(2023, Month.APRIL, 5));
    public static final MenuItem MENU_ITEM_8_NOW = new MenuItem(MENU_ITEM_8_ID, dish8, restaurant1, LocalDate.now());
    public static final MenuItem MENU_ITEM_9_NOW = new MenuItem(MENU_ITEM_9_ID, dish9, restaurant1, LocalDate.now());

    public static final List<MenuItem> FIRST_RESTAURANT_MENU_ITEMS = List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_8_NOW, MENU_ITEM_9_NOW);
    public static final List<MenuItem> FIRST_RESTAURANT_CURRENT_MENU_ITEMS = List.of(MENU_ITEM_8_NOW, MENU_ITEM_9_NOW);

    public static MenuItem getNew() {
        return new MenuItem(null, dish8, restaurant2, LocalDate.now());
    }

    public static MenuItem getUpdated() {
        return new MenuItem(MENU_ITEM_4_ID, dish7, restaurant1, LocalDate.of(2023, Month.APRIL, 5));
    }

}

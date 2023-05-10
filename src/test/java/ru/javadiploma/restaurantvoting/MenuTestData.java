package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.Menu;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static ru.javadiploma.restaurantvoting.DishTestData.dish8;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.restaurant2;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "dish", "restaurant");

    public static final int MENU_1_ID = 1;
    public static final int MENU_2_ID = 2;
    public static final int MENU_3_ID = 3;
    public static final int MENU_4_ID = 4;
    public static final int MENU_8_ID = 8;
    public static final int MENU_9_ID = 9;

    public static final Menu menu1 = new Menu(MENU_1_ID, LocalDate.of(2020, Month.APRIL, 4));
    public static final Menu menu2 = new Menu(MENU_2_ID, LocalDate.of(2020, Month.APRIL, 5));
    public static final Menu menu3 = new Menu(MENU_3_ID, LocalDate.of(2020, Month.APRIL, 4));
    public static final Menu menu4 = new Menu(MENU_4_ID, LocalDate.of(2020, Month.APRIL, 5));
    public static final Menu menu8Now = new Menu(MENU_8_ID, LocalDate.now());
    public static final Menu menu9Now = new Menu(MENU_9_ID, LocalDate.now());

    public static final List<Menu> firstRestaurantMenus = List.of(menu1, menu2, menu3, menu4, menu8Now, menu9Now);
    public static final List<Menu> firstRestaurantCurrentMenus = List.of(menu8Now, menu9Now);

    public static Menu getNew() {
        return new Menu(null, dish8, restaurant2, LocalDate.now());
    }

}

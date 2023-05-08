package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.Dish;

import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class);

    public static final int DISH_1_ID = 1;
    public static final int DISH_2_ID = 2;
    public static final int DISH_3_ID = 3;
    public static final int DISH_4_ID = 4;
    public static final int DISH_5_ID = 5;
    public static final int DISH_6_ID = 6;
    public static final int DISH_7_ID = 7;
    public static final int DISH_8_ID = 8;
    public static final int DISH_9_ID = 9;

    public static final Dish dish1 = new Dish(DISH_1_ID, "Burger", 350);
    public static final Dish dish2 = new Dish(DISH_2_ID, "Cheese Burger", 400);
    public static final Dish dish3 = new Dish(DISH_3_ID, "Cola", 80);
    public static final Dish dish4 = new Dish(DISH_4_ID, "Tea", 70);
    public static final Dish dish5 = new Dish(DISH_5_ID, "Margarita", 250);
    public static final Dish dish6 = new Dish(DISH_6_ID, "Chicago pizza", 350);
    public static final Dish dish7 = new Dish(DISH_7_ID, "Coffee", 80);
    public static final Dish dish8 = new Dish(DISH_8_ID, "Dish of the day", 300);
    public static final Dish dish9 = new Dish(DISH_9_ID, "Drink of the day", 50);

    public static final List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9);

    public static Dish getNew() {
        return new Dish(null, "New cold drink", 70);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_1_ID, "New burger", 350);
    }
}

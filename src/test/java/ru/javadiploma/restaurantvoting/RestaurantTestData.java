package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javadiploma.restaurantvoting.MenuTestData.firstRestaurantCurrentMenus;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menus");
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_MENUS_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
            //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("menus.restaurant", "menus.dish").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int RESTAURANT_1_ID = 1;
    public static final int RESTAURANT_2_ID = 2;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "Burger cafe");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_2_ID, "Pizza cafe");

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2);

    static {
        restaurant1.setMenus(firstRestaurantCurrentMenus);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New cafe");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_1_ID, "New burger cafe");
    }
}

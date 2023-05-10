package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;

import static ru.javadiploma.restaurantvoting.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {
    @Autowired
    RestaurantService restaurantService;

    @Test
    public void get() {
        Restaurant restaurant = restaurantService.get(RESTAURANT_1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    public void getWithMenu() {
        Restaurant restaurant = restaurantService.getWithMenu(RESTAURANT_1_ID);
        RESTAURANT_WITH_MENUS_MATCHER.assertMatch(restaurant, restaurant1);
    }


    @Test
    public void update() {
        restaurantService.update(new RestaurantTo(null, "New burger cafe"), RESTAURANT_1_ID);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(RESTAURANT_1_ID), getUpdated());
    }

    @Test
    public void create() {
        Restaurant created = restaurantService.create(new RestaurantTo(null, "New cafe"));
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    public void getAll() {
        RESTAURANT_MATCHER.assertMatch(restaurantService.getAll(), restaurants);
    }
}
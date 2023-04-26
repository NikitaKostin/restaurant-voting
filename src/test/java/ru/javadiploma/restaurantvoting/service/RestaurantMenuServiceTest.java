package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.RestaurantMenuTestData;
import ru.javadiploma.restaurantvoting.model.RestaurantMenu;
import ru.javadiploma.restaurantvoting.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javadiploma.restaurantvoting.RestaurantMenuTestData.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_2_ID;
import static ru.javadiploma.restaurantvoting.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantMenuServiceTest {

    @Autowired
    RestaurantMenuService restaurantMenuService;

    @Test
    public void get() {
        RestaurantMenu restaurantMenu = restaurantMenuService.get(RESTAURANT_MENU_1_ID, RESTAURANT_1_ID);
        RESTAURANT_MENU_MATCHER.assertMatch(restaurantMenu, restaurantMenu1);
    }

    @Test
    public void delete() {
        restaurantMenuService.delete(RESTAURANT_MENU_1_ID, RESTAURANT_1_ID);
        assertThrows(NotFoundException.class, () -> restaurantMenuService.get(RESTAURANT_MENU_1_ID, RESTAURANT_1_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantMenuService.delete(NOT_FOUND, RESTAURANT_1_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> restaurantMenuService.delete(RESTAURANT_MENU_1_ID, RESTAURANT_2_ID));
    }

    @Test
    public void update() {
        RestaurantMenu updated = RestaurantMenuTestData.getUpdated();
        restaurantMenuService.update(updated, RESTAURANT_1_ID);
        RESTAURANT_MENU_MATCHER.assertMatch(restaurantMenuService.get(RESTAURANT_MENU_3_ID, RESTAURANT_1_ID), updated);
    }

    @Test
    public void create() {
        RestaurantMenu created = restaurantMenuService.create(getNew(), USER_ID);
        int newId = created.id();
        RestaurantMenu newRestaurantMenu = getNew();
        newRestaurantMenu.setId(newId);
        RESTAURANT_MENU_MATCHER.assertMatch(created, newRestaurantMenu);
        RESTAURANT_MENU_MATCHER.assertMatch(restaurantMenuService.get(newId, USER_ID), newRestaurantMenu);
    }
}
package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.model.Menu;

import static ru.javadiploma.restaurantvoting.DishTestData.DISH_9_ID;
import static ru.javadiploma.restaurantvoting.MenuTestData.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.UserTestData.ADMIN_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Test
    public void get() {
        Menu menu = menuService.get(MENU_1_ID, RESTAURANT_1_ID);
        MENU_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    public void create() {
        Menu created = menuService.create(getNew(), DISH_9_ID, ADMIN_ID);
        int newId = created.id();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId, ADMIN_ID), newMenu);
    }

    @Test
    public void getAll() {
        MENU_MATCHER.assertMatch(menuService.getAll(RESTAURANT_1_ID), firstRestaurantMenus);
    }
}
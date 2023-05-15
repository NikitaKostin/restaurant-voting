package ru.javadiploma.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.MenuTestData;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.to.MenuTo;

import static ru.javadiploma.restaurantvoting.DishTestData.DISH_7_ID;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_9_ID;
import static ru.javadiploma.restaurantvoting.MenuTestData.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_2_ID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Test
    void get() {
        Menu menu = menuService.get(MENU_1_ID, RESTAURANT_1_ID);
        MENU_MATCHER.assertMatch(menu, menu1);
    }

    @Test
    void create() {
        Menu created = menuService.create(new MenuTo(null, DISH_9_ID), RESTAURANT_2_ID);
        int newId = created.id();
        Menu newMenu = MenuTestData.getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId, RESTAURANT_2_ID), newMenu);
    }

    @Test
    void update() {
        MenuTo updated = new MenuTo(null, DISH_7_ID);
        menuService.update(updated, MENU_4_ID, RESTAURANT_1_ID);
        MENU_MATCHER.assertMatch(menuService.get(MENU_4_ID, RESTAURANT_1_ID), MenuTestData.getUpdated());
    }

    @Test
    void getAll() {
        MENU_MATCHER.assertMatch(menuService.getAll(RESTAURANT_1_ID), firstRestaurantMenus);
    }
}
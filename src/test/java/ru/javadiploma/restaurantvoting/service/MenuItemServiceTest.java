package ru.javadiploma.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.MenuItemTestData;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;

import static ru.javadiploma.restaurantvoting.DishTestData.DISH_9_ID;
import static ru.javadiploma.restaurantvoting.MenuItemTestData.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_2_ID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MenuItemServiceTest {

    @Autowired
    MenuItemService menuItemService;

    @Test
    void get() {
        MenuItem menuItem = menuItemService.get(MENU_ITEM_1_ID, RESTAURANT_1_ID);
        MENU_ITEM_MATCHER.assertMatch(menuItem, MENU_ITEM_1);
    }

    @Test
    void create() {
        MenuItem created = menuItemService.create(new MenuItemTo(null, DISH_9_ID), RESTAURANT_2_ID);
        int newId = created.id();
        MenuItem newMenuItem = MenuItemTestData.getNew();
        newMenuItem.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newMenuItem);
        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(newId, RESTAURANT_2_ID), newMenuItem);
    }

    @Test
    void getAll() {
        MENU_ITEM_MATCHER.assertMatch(menuItemService.getAll(RESTAURANT_1_ID), FIRST_RESTAURANT_MENU_ITEMS);
    }
}
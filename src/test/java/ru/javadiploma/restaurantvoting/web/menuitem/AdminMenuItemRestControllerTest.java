package ru.javadiploma.restaurantvoting.web.menuitem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.service.MenuItemService;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;
import ru.javadiploma.restaurantvoting.util.JsonUtil;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_10_ID;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_4_ID;
import static ru.javadiploma.restaurantvoting.MenuItemTestData.MENU_ITEM_7_ID;
import static ru.javadiploma.restaurantvoting.MenuItemTestData.MENU_ITEM_MATCHER;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;

class AdminMenuItemRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMenuItemRestController.REST_URL + "/";

    @Autowired
    private MenuItemService menuItemService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createMenuItemWithLocation() throws Exception {
        MenuItemTo newTo = new MenuItemTo(null, DISH_10_ID);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_1_ID + "/menu-items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        MenuItem created = MENU_ITEM_MATCHER.readFromJson(action);
        int newId = created.id();
        MenuItem newMenuItem = new MenuItem(newId, LocalDate.now());
        MENU_ITEM_MATCHER.assertMatch(created, newMenuItem);
        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(newId, RESTAURANT_1_ID), newMenuItem);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateMenuItem() throws Exception {
        MenuItemTo updated = new MenuItemTo(null, DISH_4_ID);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID + "/menu-items/" + MENU_ITEM_7_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MENU_ITEM_MATCHER.assertMatch(menuItemService.get(MENU_ITEM_7_ID, RESTAURANT_1_ID), new MenuItem(MENU_ITEM_7_ID, LocalDate.of(2023, 4, 5)));
    }

}
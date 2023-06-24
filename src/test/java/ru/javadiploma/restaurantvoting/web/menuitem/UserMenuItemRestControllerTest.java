package ru.javadiploma.restaurantvoting.web.menuitem;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.MenuItemTestData.FIRST_RESTAURANT_MENU_ITEMS;
import static ru.javadiploma.restaurantvoting.MenuItemTestData.MENU_ITEM_MATCHER;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.USER_MAIL;

class UserMenuItemRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserMenuItemRestController.REST_URL + "/";

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getMenuItems() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1_ID + "/menu-items/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(FIRST_RESTAURANT_MENU_ITEMS));
    }

}
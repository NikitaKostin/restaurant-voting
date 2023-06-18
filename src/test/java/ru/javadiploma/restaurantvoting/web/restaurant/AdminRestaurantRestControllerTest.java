package ru.javadiploma.restaurantvoting.web.restaurant;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.service.MenuItemService;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;
import ru.javadiploma.restaurantvoting.util.JsonUtil;
import ru.javadiploma.restaurantvoting.util.RestaurantUtil;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_10_ID;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_4_ID;
import static ru.javadiploma.restaurantvoting.MenuItemTestData.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.*;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminRestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemService menuItemService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        RestaurantTo updated = new RestaurantTo(null, "Bob burger's");
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        val restaurant = new Restaurant(restaurant1);
        restaurant.setName(updated.getName());
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(RESTAURANT_1_ID).orElse(null), restaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        RestaurantTo newTo = new RestaurantTo(null, "Bob burger's 2");
        Restaurant newRestaurant = RestaurantUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(newId).orElse(null), newRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createMenuItem() throws Exception {
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
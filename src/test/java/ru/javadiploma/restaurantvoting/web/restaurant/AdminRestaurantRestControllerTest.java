package ru.javadiploma.restaurantvoting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.service.MenuService;
import ru.javadiploma.restaurantvoting.to.MenuTo;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;
import ru.javadiploma.restaurantvoting.util.JsonUtil;
import ru.javadiploma.restaurantvoting.util.RestaurantUtil;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_10_ID;
import static ru.javadiploma.restaurantvoting.DishTestData.DISH_4_ID;
import static ru.javadiploma.restaurantvoting.MenuTestData.MENU_7_ID;
import static ru.javadiploma.restaurantvoting.MenuTestData.MENU_MATCHER;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.*;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;

class AdminRestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminRestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuService menuService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        RestaurantTo updated = new RestaurantTo(null, "Bob burger's");
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(RESTAURANT_1_ID).orElse(null),
                RestaurantUtil.updateFromTo(new Restaurant(restaurant1), updated));
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
    void createMenu() throws Exception {
        MenuTo newTo = new MenuTo(null, DISH_10_ID);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_1_ID + "/menus/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        Menu newMenu = new Menu(newId, LocalDate.now());
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId, RESTAURANT_1_ID), newMenu);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateMenu() throws Exception {
        MenuTo updated = new MenuTo(null, DISH_4_ID);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID + "/menus/" + MENU_7_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MENU_MATCHER.assertMatch(menuService.get(MENU_7_ID, RESTAURANT_1_ID), new Menu(MENU_7_ID, LocalDate.now()));
    }
}
package ru.javadiploma.restaurantvoting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.service.DishService;
import ru.javadiploma.restaurantvoting.to.DishTo;
import ru.javadiploma.restaurantvoting.util.DishUtil;
import ru.javadiploma.restaurantvoting.util.JsonUtil;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.DishTestData.*;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;

class DishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Autowired
    private DishService dishService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void create() throws Exception {
        DishTo newTo = new DishTo(null, "New burger", 350);
        Dish newDish = DishUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}
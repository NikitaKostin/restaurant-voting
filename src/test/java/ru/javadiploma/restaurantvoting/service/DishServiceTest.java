package ru.javadiploma.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.to.DishTo;

import static ru.javadiploma.restaurantvoting.DishTestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DishServiceTest {
    @Autowired
    DishService dishService;

    @Test
    void get() {
        Dish dish = dishService.get(DISH_1_ID);
        DISH_MATCHER.assertMatch(dish, dish1);
    }

    @Test
    void getAll() {
        DISH_MATCHER.assertMatch(dishService.getAll(), dishes);
    }

    @Test
    void create() {
        Dish created = dishService.create(new DishTo(null, "New cold drink", 70));
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}
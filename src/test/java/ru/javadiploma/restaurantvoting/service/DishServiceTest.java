package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.model.Dish;

import static ru.javadiploma.restaurantvoting.DishTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {

    @Autowired
    DishService dishService;

    @Test
    public void get() {
        Dish dish = dishService.get(DISH_1_ID);
        DISH_MATCHER.assertMatch(dish, dish1);
    }

    @Test
    public void getAll() {
        DISH_MATCHER.assertMatch(dishService.getAll(), dishes);
    }

    @Test
    public void create() {
        Dish created = dishService.create(getNew());
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        dishService.update(updated);
        DISH_MATCHER.assertMatch(dishService.get(DISH_1_ID), getUpdated());
    }
}
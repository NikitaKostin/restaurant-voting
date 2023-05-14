package ru.javadiploma.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Dish;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DishServiceTest {
    @Autowired
    DishService dishService;

    @Test
    void get() {
        final Dish dish = dishService.get(1);
        System.out.println(dish);
    }
}
package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javadiploma.restaurantvoting.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void create() {
        User created = userService.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    public void get() {
        User user = userService.get(ADMIN_ID);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = userService.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        userService.update(updated);
        USER_MATCHER.assertMatch(userService.get(USER_ID), getUpdated());
    }
}
package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.UserVoteTestData;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.model.UserVote;

import static org.junit.Assert.*;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_MATCHER;
import static ru.javadiploma.restaurantvoting.UserTestData.USER_ID;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserVoteServiceTest {
    @Autowired
    UserVoteService userVoteService;

    @Test
    public void create() {
        UserVote created = userVoteService.create(getNew(), USER_ID, RESTAURANT_1_ID);
        int newId = created.id();
        UserVote newUserVote = getNew();
        newUserVote.setId(newId);
        USER_VOTE_MATCHER.assertMatch(created, newUserVote);
        USER_VOTE_MATCHER.assertMatch(userVoteService.get(newId, USER_ID, RESTAURANT_1_ID), newUserVote);
    }

    @Test
    public void createAfter11() {
        UserVote created = userVoteService.create(getNewAfter11(), USER_ID, RESTAURANT_1_ID);
        assertNull(created);
    }

    @Test
    public void get() {
        UserVote userVote = userVoteService.get(USER_VOTE_1_ID, USER_ID, RESTAURANT_1_ID);
        USER_VOTE_MATCHER.assertMatch(userVote, userVote1);
    }
}
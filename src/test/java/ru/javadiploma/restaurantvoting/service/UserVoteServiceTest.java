package ru.javadiploma.restaurantvoting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNull;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.UserTestData.USER_ID;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserVoteServiceTest {
    // зачем поиск по id и поиск со всеми данными
    @Autowired
    UserVoteService userVoteService;

    @Test
    public void vote() {
        UserVote created = userVoteService.vote(new UserVoteTo(RESTAURANT_1_ID, voteDateTime), USER_ID);
        int newId = created.id();
        UserVote newUserVote = getNew();
        newUserVote.setId(newId);
        USER_VOTE_MATCHER.assertMatch(created, newUserVote);
        USER_VOTE_MATCHER.assertMatch(userVoteService.get(newId, USER_ID, RESTAURANT_1_ID), newUserVote);
    }

    @Test
    public void createAfter11() {
        UserVote created = userVoteService.vote(new UserVoteTo(RESTAURANT_1_ID, LocalDateTime.of(2023, 1, 1, 23, 0)), USER_ID);
        assertNull(created);
    }

    @Test
    public void get() {
        UserVote userVote = userVoteService.get(USER_VOTE_1_ID, USER_ID, RESTAURANT_1_ID);
        USER_VOTE_MATCHER.assertMatch(userVote, userVote1);
    }
}
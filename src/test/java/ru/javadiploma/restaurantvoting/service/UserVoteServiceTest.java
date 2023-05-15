package ru.javadiploma.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.*;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.USER_ID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class UserVoteServiceTest {
    @Autowired
    UserVoteService userVoteService;

    @Test
    void get() {
        UserVote userVote = userVoteService.get(USER_VOTE_1_ID, USER_ID, RESTAURANT_1_ID);
        USER_VOTE_MATCHER.assertMatch(userVote, userVote1);
    }

    @Test
    void vote() {
        UserVote created = userVoteService.vote(new UserVoteTo(null, RESTAURANT_1_ID), USER_ID);
        int newId = created.id();
        UserVote newUserVote = getNew();
        newUserVote.setId(newId);
        USER_VOTE_MATCHER.assertMatch(created, newUserVote);
        USER_VOTE_MATCHER.assertMatch(userVoteService.get(newId, USER_ID, RESTAURANT_1_ID), newUserVote);
    }
}
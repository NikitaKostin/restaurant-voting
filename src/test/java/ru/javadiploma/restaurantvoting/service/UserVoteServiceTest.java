package ru.javadiploma.restaurantvoting.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.error.ResourceNotFoundException;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import java.util.Objects;

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

    @Autowired
    UserVoteRepository userVoteRepository;

    @Test
    void vote() {
        UserVote created = userVoteService.vote(new UserVoteTo(null, RESTAURANT_1_ID), USER_ID);
        int newId = created.id();
        UserVote newUserVote = getNew();
        newUserVote.setId(newId);
        USER_VOTE_MATCHER.assertMatch(created, newUserVote);
        val userVoteFromDb = userVoteRepository.findById(newId)
                .filter(userVote -> Objects.equals(userVote.getUser().getId(), USER_ID) &&
                        Objects.equals(userVote.getRestaurant().getId(), RESTAURANT_1_ID)
                )
                .orElseThrow(
                        () -> new ResourceNotFoundException("User vote with id " + newId + " not found")
                );
        USER_VOTE_MATCHER.assertMatch(userVoteFromDb, newUserVote);
    }
}
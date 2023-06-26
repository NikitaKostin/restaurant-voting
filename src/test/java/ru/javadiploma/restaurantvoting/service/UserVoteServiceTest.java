package ru.javadiploma.restaurantvoting.service;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.UserVoteTestData;
import ru.javadiploma.restaurantvoting.error.IllegalRequestDataException;
import ru.javadiploma.restaurantvoting.error.ResourceNotFoundException;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.javadiploma.restaurantvoting.RestaurantTestData.*;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.USER_VOTE_MATCHER;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.allowedVoteTime;
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

    @Autowired
    UserRepository userRepository;

    @Test
    void vote() {
        val userVote = userVoteRepository.getByUserAndVoteDate(userRepository.getById(USER_ID), LocalDate.now());

        if (userVote.isPresent() && !LocalDateTime.now().toLocalTime().isBefore(allowedVoteTime)) {
            Assertions.assertThrows(IllegalRequestDataException.class,
                    () -> userVoteService.vote(new UserVoteTo(null, RESTAURANT_1_ID), USER_ID));
        } else {
            UserVote created = userVoteService.vote(new UserVoteTo(null, RESTAURANT_1_ID), USER_ID);
            int newId = created.id();
            UserVote newUserVote = UserVoteTestData.getNew();
            newUserVote.setId(newId);
            USER_VOTE_MATCHER.assertMatch(created, newUserVote);
            val userVoteFromDb = userVoteRepository.getWithRestaurant(newId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("User vote with id " + newId + " not found")
                    );
            val restaurant1FromUserVote = new Restaurant(userVoteFromDb.getRestaurant().getId(), userVoteFromDb.getRestaurant().getName());
            USER_VOTE_MATCHER.assertMatch(userVoteFromDb, newUserVote);
            RESTAURANT_MATCHER.assertMatch(restaurant1FromUserVote, restaurant1);
        }
    }
}
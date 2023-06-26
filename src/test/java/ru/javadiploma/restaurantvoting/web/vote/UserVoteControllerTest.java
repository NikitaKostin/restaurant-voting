package ru.javadiploma.restaurantvoting.web.vote;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.error.ResourceNotFoundException;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;
import ru.javadiploma.restaurantvoting.util.JsonUtil;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.*;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.USER_VOTE_MATCHER;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.allowedVoteTime;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.USER_ID;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static ru.javadiploma.restaurantvoting.web.vote.UserVoteController.REST_URL;

class UserVoteControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserVoteRepository userVoteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void vote() throws Exception {
        UserVoteTo newTo = new UserVoteTo(null, RESTAURANT_1_ID);
        UserVote newUserVote = new UserVote(
                null,
                userRepository.getById(USER_ID),
                restaurantRepository.getById(newTo.getRestaurantId())
        );
        val oldUserVote = userVoteRepository.getByUserAndVoteDateEquals(userRepository.getById(USER_ID), LocalDate.now());
        val inTime = oldUserVote.isEmpty() || LocalDateTime.now().toLocalTime().isBefore(allowedVoteTime);

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(inTime ? status().isCreated() : status().isUnprocessableEntity());

        if (inTime) {
            UserVote created = USER_VOTE_MATCHER.readFromJson(action);
            int newId = created.id();
            newUserVote.setId(newId);
            USER_VOTE_MATCHER.assertMatch(created, newUserVote);
            val userVoteFromDb = userVoteRepository.findById(newId)
                    .filter(userVote -> Objects.equals(userVote.getUser().getId(), USER_ID) &&
                            Objects.equals(userVote.getRestaurant().getId(), RESTAURANT_1_ID)
                    )
                    .orElseThrow(
                            () -> new ResourceNotFoundException("User vote with id " + newId + " not found")
                    );
            val restaurant1FromUserVote = new Restaurant(userVoteFromDb.getRestaurant().getId(), userVoteFromDb.getRestaurant().getName());
            USER_VOTE_MATCHER.assertMatch(userVoteFromDb, newUserVote);
            RESTAURANT_MATCHER.assertMatch(restaurant1FromUserVote, restaurant1);
        }

    }
}
package ru.javadiploma.restaurantvoting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.service.UserVoteService;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;
import ru.javadiploma.restaurantvoting.util.JsonUtil;
import ru.javadiploma.restaurantvoting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.USER_VOTE_MATCHER;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.USER_ID;
import static ru.javadiploma.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static ru.javadiploma.restaurantvoting.web.vote.UserVoteController.REST_URL;

class UserVoteControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserVoteService userVoteService;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void vote() throws Exception {
        UserVoteTo newTo = new UserVoteTo(null, RESTAURANT_1_ID);
        UserVote newUserVote = new UserVote(
                null,
                userRepository.getById(USER_ID),
                restaurantRepository.getById(newTo.getRestaurantId())
        );
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        UserVote created = USER_VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newUserVote.setId(newId);
        USER_VOTE_MATCHER.assertMatch(created, newUserVote);
        USER_VOTE_MATCHER.assertMatch(userVoteService.get(newId, USER_ID, RESTAURANT_1_ID), newUserVote);
    }
}
package ru.javadiploma.restaurantvoting.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javadiploma.restaurantvoting.AbstractControllerTest;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.service.UserVoteService;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;
import ru.javadiploma.restaurantvoting.util.SecurityUtil;
import ru.javadiploma.restaurantvoting.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javadiploma.restaurantvoting.RestaurantTestData.RESTAURANT_1_ID;
import static ru.javadiploma.restaurantvoting.UserVoteTestData.*;
import static ru.javadiploma.restaurantvoting.web.user.UserRestController.REST_URL;

public class UserRestControllerTest extends AbstractControllerTest {
    @Autowired
    UserVoteService userVoteService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void vote() throws Exception {
        int userId = SecurityUtil.authUserId();
        UserVoteTo newTo = new UserVoteTo(RESTAURANT_1_ID, voteDateTime);
        UserVote newUserVote = new UserVote(
                null,
                userRepository.getReferenceById(userId),
                restaurantRepository.getReferenceById(newTo.getRestaurantId()),
                newTo.getVoteDateTime()
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
        USER_VOTE_MATCHER.assertMatch(userVoteService.get(newId, userId, RESTAURANT_1_ID), newUserVote);
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + USER_VOTE_1_ID + "/restaurants/" + RESTAURANT_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_VOTE_MATCHER.contentJson(userVote1));
    }
}
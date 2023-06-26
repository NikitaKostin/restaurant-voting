package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.javadiploma.restaurantvoting.RestaurantTestData.restaurant1;

public class UserVoteTestData {
    public static final MatcherFactory.Matcher<UserVote> USER_VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(UserVote.class, "user", "restaurant", "voteDate");

    public static final int USER_VOTE_1_ID = 1;

    public static final LocalTime allowedVoteTime = LocalTime.of(11, 0, 0);
    public static final UserVote userVote1 = new UserVote(USER_VOTE_1_ID);
    public static final LocalDate voteDate = LocalDate.now();

    public static UserVote getNew() {
        return new UserVote(null, restaurant1, voteDate);
    }
}

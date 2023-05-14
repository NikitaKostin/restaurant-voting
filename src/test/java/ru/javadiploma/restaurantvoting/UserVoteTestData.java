package ru.javadiploma.restaurantvoting;

import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.web.MatcherFactory;

import java.time.LocalDateTime;

public class UserVoteTestData {
    public static final MatcherFactory.Matcher<UserVote> USER_VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(UserVote.class, "user", "restaurant", "voteDateTime");

    public static final int USER_VOTE_1_ID = 1;

    public static final UserVote userVote1 = new UserVote(USER_VOTE_1_ID);
    public static final LocalDateTime voteDateTime = LocalDateTime.now();

    public static UserVote getNew() {
        return new UserVote(null, voteDateTime);
    }
}

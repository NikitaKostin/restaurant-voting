package ru.javadiploma.restaurantvoting.repository;

import ru.javadiploma.restaurantvoting.model.UserVote;

import java.util.List;

public interface UserVoteRepository {
    // null if updated userVote does not belong userId or restaurantId
    UserVote save(UserVote userVote, int userId, int restaurantId);

    // null if userVote does not belong userId or restaurantId
    UserVote get(int id, int userId, int restaurantId);
}

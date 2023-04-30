package ru.javadiploma.restaurantvoting.repository;

import ru.javadiploma.restaurantvoting.model.UserVote;

public interface UserVoteRepository {
    // null if updated userVote does not belong userId or restaurantId
    UserVote save(UserVote userVote, int userId, int restaurantId);

    // null if userVote does not belong userId or restaurantId
    UserVote get(int id, int userId, int restaurantId);

    default UserVote getWithUserAndRestaurant(int id, int userId, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}

package ru.javadiploma.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserVoteService {
    private final UserVoteRepository userVoteRepository;

    public UserVoteService(UserVoteRepository userVoteRepository) {
        this.userVoteRepository = userVoteRepository;
    }

    public UserVote create(UserVote userVote, int userId, int restaurantId) {
        Assert.notNull(userVote, "userVote must not be null");

        return userVoteRepository.save(userVote, userId, restaurantId);
    }

    public UserVote get(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(userVoteRepository.get(id, userId, restaurantId), id);
    }

    public UserVote getWithUserAndRestaurant(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(userVoteRepository.getWithUserAndRestaurant(id, userId, restaurantId), id);
    }
}

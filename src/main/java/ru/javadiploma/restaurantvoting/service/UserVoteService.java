package ru.javadiploma.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Service
public class UserVoteService {
    @Autowired
    protected UserVoteRepository userVoteRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    public UserVote get(int id, int userId, int restaurantId) {
        return userVoteRepository.findById(id)
                .filter(userVote -> Objects.equals(userVote.getUser().getId(), userId) &&
                        Objects.equals(userVote.getRestaurant().getId(), restaurantId)
                )
                .orElse(null);
    }

    public UserVote vote(UserVoteTo userVoteTo, int userId) {
        if (LocalDateTime.now().toLocalTime().isBefore(LocalTime.of(23, 0, 0))) {
            return userVoteRepository.save(new UserVote(
                    null,
                    userRepository.getById(userId),
                    restaurantRepository.getById(userVoteTo.getRestaurantId())
            ));
        }
        return null;
    }
}

package ru.javadiploma.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import java.time.LocalTime;
import java.util.Objects;

@Service
public class UserVoteService {
    private final UserVoteRepository userVoteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public UserVoteService(UserVoteRepository userVoteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userVoteRepository = userVoteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public UserVote get(int id, int userId, int restaurantId) {
        return userVoteRepository.findById(id)
                .filter(userVote -> Objects.equals(userVote.getUser().getId(), userId) &&
                        Objects.equals(userVote.getRestaurant().getId(), restaurantId)
                )
                .orElse(null);
    }

    @Transactional
    public UserVote vote(UserVoteTo userVoteTo, int userId) {
        if (userVoteTo.getVoteDateTime().toLocalTime().isBefore(LocalTime.of(23, 0, 0))) {
            return userVoteRepository.save(new UserVote(
                    null,
                    userRepository.getReferenceById(userId),
                    restaurantRepository.getReferenceById(userVoteTo.getRestaurantId()),
                    userVoteTo.getVoteDateTime()
            ));
        }
        return null;
    }
}

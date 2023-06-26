package ru.javadiploma.restaurantvoting.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javadiploma.restaurantvoting.error.IllegalRequestDataException;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class UserVoteService {
    @Autowired
    protected UserVoteRepository userVoteRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    public UserVote vote(UserVoteTo userVoteTo, int userId) {
        val userVote = userVoteRepository.getByUserAndVoteDate(userRepository.getById(userId), LocalDate.now());

        if (userVote.isPresent()) {
            if (LocalDateTime.now().toLocalTime().isBefore(LocalTime.of(11, 0, 0))) {
                userVote
                        .ifPresent(it -> {
                            it.setRestaurant(restaurantRepository.getById(userVoteTo.getRestaurantId()));
                            userVoteRepository.save(it);
                        });
            } else {
                throw new IllegalRequestDataException("To late to change vote");
            }
        } else {
            return userVoteRepository.save(new UserVote(
                    null,
                    userRepository.getById(userId),
                    restaurantRepository.getById(userVoteTo.getRestaurantId())
            ));
        }
        return userVote.get();
    }
}

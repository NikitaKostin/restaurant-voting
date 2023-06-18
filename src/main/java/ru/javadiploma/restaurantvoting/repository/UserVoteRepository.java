package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.model.UserVote;

import java.time.LocalDate;
import java.util.Optional;

public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {
    Optional<UserVote> getByUserAndVoteDate(User user, LocalDate voteDate);
}

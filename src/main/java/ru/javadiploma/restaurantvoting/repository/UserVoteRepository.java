package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javadiploma.restaurantvoting.model.UserVote;

public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {
}

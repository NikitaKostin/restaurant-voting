package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.UserVote;

@Repository
public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {
}

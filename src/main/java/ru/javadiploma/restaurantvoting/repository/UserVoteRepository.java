package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.model.UserVote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {

    Optional<UserVote> getByUserAndVoteDateEquals(User user, LocalDate voteDate);

    @Query("""
             SELECT uv FROM UserVote uv
             JOIN FETCH uv.restaurant AS r
             WHERE uv.id = :id
            """)
    Optional<UserVote> getWithRestaurant(int id);

    List<UserVote> getAllByUser(User user);
}

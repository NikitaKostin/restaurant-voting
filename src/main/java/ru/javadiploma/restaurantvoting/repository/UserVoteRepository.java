package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.model.UserVote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<UserVote> getByUserAndVoteDate(User user, LocalDate voteDate);

    @Query("""
             SELECT uv FROM UserVote uv
             JOIN FETCH uv.restaurant AS r
             WHERE uv.id = :id
            """)
    Optional<UserVote> getWithRestaurant(int id);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<UserVote> getAllByUserOrderByVoteDateDesc(User user);
}

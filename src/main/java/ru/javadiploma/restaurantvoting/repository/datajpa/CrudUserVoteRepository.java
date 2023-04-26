package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.UserVote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserVoteRepository extends JpaRepository<UserVote, Integer> {
    Optional<UserVote> findByIdAndUserIdAndRestaurantId(Integer id, Integer userId, Integer restaurantId);
}

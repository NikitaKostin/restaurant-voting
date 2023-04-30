package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.UserVote;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserVoteRepository extends JpaRepository<UserVote, Integer> {
    Optional<UserVote> findByIdAndUserIdAndRestaurantId(int id, int userId, int restaurantId);

    @Query("SELECT uv FROM UserVote uv JOIN FETCH uv.user JOIN FETCH uv.restaurant" +
            " WHERE uv.id= ?1 AND uv.user.id = ?2 AND uv.restaurant.id= ?3")
    Optional<UserVote> getWithUserAndRestaurant(int id, int userId, int restaurantId);
}

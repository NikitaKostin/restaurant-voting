package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javadiploma.restaurantvoting.model.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menuItems m JOIN FETCH m.dish WHERE r.id= ?1 AND m.offerDate = current_date")
    Optional<Restaurant> getWithMenuItems(int id);
}

package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javadiploma.restaurantvoting.model.Restaurant;

import java.time.LocalDate;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menuItems m JOIN FETCH m.dish WHERE r.id= ?1 AND m.offerDate = ?2")
    Optional<Restaurant> getMenuItemsWithDishByDate(int id, LocalDate offerDate);
}

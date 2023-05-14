package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menus m WHERE r.id= ?1 AND m.createDate = current_date ")
    Optional<Restaurant> getWithRestaurant(int id);
}

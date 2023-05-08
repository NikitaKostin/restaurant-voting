package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Restaurant;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findById(int id);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menus m WHERE r.id= ?1 AND m.createDate = current_date ")
    Optional<Restaurant> getWithRestaurant(int id);
}

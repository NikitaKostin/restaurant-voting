package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.model.RestaurantMenu;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findById(Integer id);
}

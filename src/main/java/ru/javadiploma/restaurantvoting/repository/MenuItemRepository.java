package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.MenuItem;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Query("SELECT mi FROM MenuItem mi WHERE mi.restaurant.id=:restaurantId")
    List<MenuItem> getAll(@Param("restaurantId") int restaurantId);

    @Query("""
            SELECT mi FROM MenuItem mi
            JOIN FETCH mi.restaurant AS r
            JOIN FETCH mi.dish AS d
            WHERE mi.id = :id
                AND r.id = :restaurantId
           """)
    Optional<MenuItem> getWithRestaurantAndDish(int id, int restaurantId);
}
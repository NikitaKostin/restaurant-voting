package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.MenuItem;

import java.util.List;

@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Query("SELECT mi FROM MenuItem mi WHERE mi.restaurant.id=:restaurantId")
    List<MenuItem> getAll(@Param("restaurantId") int restaurantId);
}
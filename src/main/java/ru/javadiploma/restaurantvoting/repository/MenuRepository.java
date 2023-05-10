package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Menu;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT rm FROM Menu rm WHERE rm.restaurant.id=:restaurantId")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);
}

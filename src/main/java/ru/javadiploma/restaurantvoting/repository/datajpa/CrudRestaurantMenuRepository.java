package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.RestaurantMenu;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantMenuRepository extends JpaRepository<RestaurantMenu, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM RestaurantMenu rm WHERE rm.id=:id AND rm.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    Optional<RestaurantMenu> findByIdAndRestaurantId(Integer id, Integer restaurantId);

    @Query("SELECT rm FROM RestaurantMenu rm WHERE rm.restaurant.id=:restaurantId")
    List<RestaurantMenu> getAll(@Param("restaurantId") int restaurantId);
}

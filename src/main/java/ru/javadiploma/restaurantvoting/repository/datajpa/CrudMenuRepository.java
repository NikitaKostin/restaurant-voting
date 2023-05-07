package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Menu;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT rm FROM Menu rm WHERE rm.restaurant.id=:restaurantId")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT rm FROM Menu rm JOIN FETCH rm.restaurant WHERE rm.id= ?1 AND rm.restaurant.id= ?2")
    Optional<Menu> getWithRestaurant(int id, int restaurantId);
}

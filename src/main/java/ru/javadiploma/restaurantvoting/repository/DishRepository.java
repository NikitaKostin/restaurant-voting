package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}

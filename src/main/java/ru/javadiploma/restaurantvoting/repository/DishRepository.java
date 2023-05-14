package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
}

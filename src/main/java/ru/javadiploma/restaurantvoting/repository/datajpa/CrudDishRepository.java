package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javadiploma.restaurantvoting.model.Dish;

public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
}

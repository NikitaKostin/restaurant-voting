package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javadiploma.restaurantvoting.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}

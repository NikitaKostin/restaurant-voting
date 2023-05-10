package ru.javadiploma.restaurantvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
package ru.javadiploma.restaurantvoting.repository;

import ru.javadiploma.restaurantvoting.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);
}
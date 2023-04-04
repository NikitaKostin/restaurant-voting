package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // null if not found
    User get(int id);

    List<User> getAll();
}

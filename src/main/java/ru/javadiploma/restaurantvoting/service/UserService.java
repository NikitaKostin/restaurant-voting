package ru.javadiploma.restaurantvoting.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.util.ValidationUtil;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return ValidationUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(user), user.id());
    }
}
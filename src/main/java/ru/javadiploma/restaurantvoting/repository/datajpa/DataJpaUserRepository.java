package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.User;
import ru.javadiploma.restaurantvoting.repository.UserRepository;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudUserRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }
}

package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DataJpaUserVoteRepository implements UserVoteRepository {
    private final CrudUserVoteRepository crudUserVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaUserVoteRepository(CrudUserVoteRepository crudUserVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudUserVoteRepository = crudUserVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public UserVote save(UserVote userVote, int userId, int restaurantId) {
        if (userVote.isNew() && userVote.getVoteDateTime().toLocalTime().isBefore(LocalTime.of(23, 0, 0))) {
            userVote.setUser(crudUserRepository.getReferenceById(userId));
            userVote.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
            return crudUserVoteRepository.save(userVote);
        }
        return null;
    }

    @Override
    public UserVote get(int id, int userId, int restaurantId) {
        return crudUserVoteRepository.findByIdAndUserIdAndRestaurantId(id, userId, restaurantId).orElse(null);
    }
}

package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }
}

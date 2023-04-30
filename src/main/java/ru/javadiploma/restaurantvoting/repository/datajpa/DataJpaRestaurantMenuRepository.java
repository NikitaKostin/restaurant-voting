package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.RestaurantMenu;
import ru.javadiploma.restaurantvoting.repository.RestaurantMenuRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantMenuRepository implements RestaurantMenuRepository {
    private final CrudRestaurantMenuRepository crudRestaurantMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRestaurantMenuRepository(CrudRestaurantMenuRepository crudRestaurantMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantMenuRepository = crudRestaurantMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public RestaurantMenu save(RestaurantMenu restaurantMenu, int restaurantId) {
        restaurantMenu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
        if (restaurantMenu.isNew()) {
            return crudRestaurantMenuRepository.save(restaurantMenu);
        }
        return get(restaurantMenu.id(), restaurantId) == null
                ? null
                : crudRestaurantMenuRepository.save(restaurantMenu);
    }

    @Override
    public RestaurantMenu get(int id, int restaurantId) {
        return crudRestaurantMenuRepository.findByIdAndRestaurantId(id, restaurantId).orElse(null);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudRestaurantMenuRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public List<RestaurantMenu> getAll(int restaurantId) {
        return crudRestaurantMenuRepository.getAll(restaurantId);
    }
}

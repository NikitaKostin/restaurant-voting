package ru.javadiploma.restaurantvoting.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.repository.MenuRepository;

import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudDishRepository crudDishRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository, CrudDishRepository crudDishRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudDishRepository = crudDishRepository;
    }

    @Override
    public Menu save(Menu menu, int dishId, int restaurantId) {
        if (!menu.isNew() && get(menu.id(), restaurantId) == null) {
            return null;
        }
        menu.setDish(crudDishRepository.getReferenceById(dishId));
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> Objects.equals(menu.getRestaurant().getId(), restaurantId))
                .orElse(null);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }
}

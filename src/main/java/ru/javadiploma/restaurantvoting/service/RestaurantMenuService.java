package ru.javadiploma.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javadiploma.restaurantvoting.model.RestaurantMenu;
import ru.javadiploma.restaurantvoting.repository.RestaurantMenuRepository;
import ru.javadiploma.restaurantvoting.util.ValidationUtil;

import java.util.List;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantMenuService {
    private final RestaurantMenuRepository restaurantMenuRepository;

    public RestaurantMenuService(RestaurantMenuRepository restaurantMenuRepository) {
        this.restaurantMenuRepository = restaurantMenuRepository;
    }

    public RestaurantMenu get(int id, int restaurantId) {
        return checkNotFoundWithId(restaurantMenuRepository.get(id, restaurantId), id);
    }

    public RestaurantMenu getWithRestaurant(int id, int userId) {
        return checkNotFoundWithId(restaurantMenuRepository.getWithRestaurant(id, userId), id);
    }

    public void delete(int id, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(restaurantMenuRepository.delete(id, restaurantId), id);
    }

    public void update(RestaurantMenu restaurantMenu, int restaurantId) {
        Assert.notNull(restaurantMenu, "menu must not be null");
        checkNotFoundWithId(restaurantMenuRepository.save(restaurantMenu, restaurantId), restaurantMenu.id());
    }

    public RestaurantMenu create(RestaurantMenu restaurantMenu, int restaurantId) {
        Assert.notNull(restaurantMenu, "menu must not be null");
        return restaurantMenuRepository.save(restaurantMenu, restaurantId);
    }

    public List<RestaurantMenu> getAll(int restaurantId) {
        return restaurantMenuRepository.getAll(restaurantId);
    }
}

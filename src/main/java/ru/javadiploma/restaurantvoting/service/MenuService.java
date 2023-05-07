package ru.javadiploma.restaurantvoting.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.repository.MenuRepository;

import java.util.List;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    public Menu getWithRestaurant(int id, int userId) {
        return checkNotFoundWithId(menuRepository.getWithRestaurant(id, userId), id);
    }

    @CacheEvict(value = "menu", allEntries = true)
    public Menu create(Menu menu, int dishId, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return menuRepository.save(menu, dishId, restaurantId);
    }

    @Cacheable("menu")
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }
}

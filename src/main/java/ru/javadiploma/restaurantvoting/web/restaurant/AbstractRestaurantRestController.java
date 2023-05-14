package ru.javadiploma.restaurantvoting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.service.MenuService;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantRestController {
    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected MenuService menuService;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant getWithMenu(int id) {
        log.info("get with menu {}", id);
        return restaurantRepository.getWithMenu(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        log.info("get all");
        return restaurantRepository.findAll();
    }

    public List<Menu> getMenus(int id) {
        log.info("get menus for restaurantId {}", id);
        return menuService.getAll(id);
    }

    public Menu getMenu(int menuId, int restaurantId) {
        log.info("get menu with menuId {} for restaurantId {}", menuId, restaurantId);
        return menuService.get(menuId, restaurantId);
    }
}

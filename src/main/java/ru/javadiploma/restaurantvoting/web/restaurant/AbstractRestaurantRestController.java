package ru.javadiploma.restaurantvoting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.service.MenuItemService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class AbstractRestaurantRestController {
    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected MenuItemService menuItemService;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant getMenuItemsWithDish(int id) {
        log.info("get menu items {} with dish", id);
        return restaurantRepository.getMenuItemsWithDishByDate(id, LocalDate.now()).orElse(null);
    }

    public List<Restaurant> getAll() {
        log.info("get all");
        return restaurantRepository.findAll();
    }

    public List<MenuItem> getMenuItems(int id) {
        log.info("get menu items for restaurantId {}", id);
        return menuItemService.getAll(id);
    }

    public MenuItem getMenuItem(int menuItemId, int restaurantId) {
        log.info("get menu item with menuItemId {} for restaurantId {}", menuItemId, restaurantId);
        return menuItemService.get(menuItemId, restaurantId);
    }
}

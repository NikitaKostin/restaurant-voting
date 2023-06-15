package ru.javadiploma.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.javadiploma.restaurantvoting.error.ResourceNotFoundException;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.repository.MenuItemRepository;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;

@Service
public class MenuItemService {
    @Autowired
    protected MenuItemRepository menuItemRepository;
    @Autowired
    protected DishRepository dishRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    public MenuItem get(int menuItemId, int restaurantId) {
        return menuItemRepository.findById(menuItemId)
                .filter(menuItem -> Objects.equals(menuItem.getRestaurant().getId(), restaurantId))
                .orElseThrow(
                        () -> new ResourceNotFoundException("Menu item with id " + menuItemId + " not found")
                );
    }

    @CacheEvict(value = "menuItem", allEntries = true)
    @Transactional
    public MenuItem create(MenuItemTo menuItemTo, int restaurantId) {
        MenuItem menuItem = new MenuItem(
                null,
                dishRepository.getById(menuItemTo.getDishId()),
                restaurantRepository.getById(restaurantId),
                LocalDate.now()
        );
        return menuItemRepository.save(menuItem);
    }

    @CacheEvict(value = "menuItem", allEntries = true)
    @Transactional
    public void update(MenuItemTo menuItemTo, int menuId, int restaurantId) {
        assureIdConsistent(menuItemTo, menuId);
        MenuItem menuItem = get(menuItemTo.id(), restaurantId);
        menuItem.setDish(dishRepository.getById(menuItemTo.getDishId()));
    }

    @Cacheable("menuItem")
    public List<MenuItem> getAll(int restaurantId) {
        return menuItemRepository.getAll(restaurantId);
    }
}

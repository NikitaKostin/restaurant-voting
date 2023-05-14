package ru.javadiploma.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.repository.MenuRepository;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.to.MenuTo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;

@Service
public class MenuService {
    @Autowired
    protected MenuRepository menuRepository;
    @Autowired
    protected DishRepository dishRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    public Menu get(int menuId, int restaurantId) {
        return menuRepository.findById(menuId)
                .filter(menu -> Objects.equals(menu.getRestaurant().getId(), restaurantId)).orElse(null);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    public Menu create(MenuTo menuTo, int restaurantId) {
        Menu menu = new Menu(
                null,
                dishRepository.getById(menuTo.getDishId()),
                restaurantRepository.getById(restaurantId),
                LocalDate.now()
        );
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    public void update(MenuTo menuTo, int menuId, int restaurantId) {
        assureIdConsistent(menuTo, menuId);
        Menu menu = get(menuTo.id(), restaurantId);
        menu.setDish(dishRepository.getById(menuTo.getDishId()));
    }

    @Cacheable("menu")
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }
}

package ru.javadiploma.restaurantvoting.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.repository.MenuRepository;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.to.MenuTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(menuRepository.findById(id)
                .filter(menu -> Objects.equals(menu.getRestaurant().getId(), restaurantId))
                .orElse(null), id);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    public Menu create(MenuTo menuTo, int restaurantId) {
        Menu menu = new Menu(
                null,
                dishRepository.getReferenceById(menuTo.getDishId()),
                restaurantRepository.getReferenceById(restaurantId),
                LocalDate.now()
        );
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    public void update(MenuTo menuTo, int id, int restaurantId) {
        assureIdConsistent(menuTo, id);
        Menu menu = get(menuTo.id(), restaurantId);
        menu.setDish(dishRepository.getReferenceById(menuTo.getDishId()));
    }

    @Cacheable("menu")
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }
}

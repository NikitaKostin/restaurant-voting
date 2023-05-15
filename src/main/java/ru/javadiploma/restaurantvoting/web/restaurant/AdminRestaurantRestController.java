package ru.javadiploma.restaurantvoting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.service.MenuService;
import ru.javadiploma.restaurantvoting.to.MenuTo;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;
import ru.javadiploma.restaurantvoting.util.RestaurantUtil;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController extends AbstractRestaurantRestController {
    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected MenuService menuService;

    static final String REST_URL = "/api/rest/admin/restaurant";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        return super.getWithMenu(id);
    }

    @GetMapping
    @Cacheable("restaurant")
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurant", allEntries = true)
    @Transactional
    public void update(@RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        assureIdConsistent(restaurantTo, id);
        Restaurant restaurant = get(restaurantTo.id());
        RestaurantUtil.updateFromTo(restaurant, restaurantTo);
        restaurantRepository.save(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "restaurant", allEntries = true)
    public Restaurant create(@RequestBody RestaurantTo restaurantTo) {
        return restaurantRepository.save(RestaurantUtil.createNewFromTo(restaurantTo));
    }

    @GetMapping("/{id}/menus/{menuId}")
    public Menu getMenu(@PathVariable int id, @PathVariable int menuId) {
        return super.getMenu(menuId, id);
    }

    @GetMapping("/{id}/menus")
    public List<Menu> getMenus(@PathVariable int id) {
        return super.getMenus(id);
    }

    @PostMapping("/{id}/menus")
    @ResponseStatus(HttpStatus.CREATED)
    public Menu createMenu(@Valid @RequestBody MenuTo menuTo, @PathVariable int id) {
        return menuService.create(menuTo, id);
    }

    @PutMapping(value = "/{id}/menus/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMenu(@Valid @RequestBody MenuTo menuTo, @PathVariable int id, @PathVariable int menuId) {
        menuService.update(menuTo, menuId, id);
    }
}

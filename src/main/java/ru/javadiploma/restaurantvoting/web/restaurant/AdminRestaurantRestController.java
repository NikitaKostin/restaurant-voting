package ru.javadiploma.restaurantvoting.web.restaurant;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.service.MenuItemService;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;
import ru.javadiploma.restaurantvoting.util.RestaurantUtil;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController extends AbstractRestaurantRestController {
    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected MenuItemService menuItemService;

    static final String REST_URL = "/api/rest/admin/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/menu-items-with-dish")
    public Restaurant getMenuItemsWithDish(@PathVariable int id) {
        return super.getMenuItemsWithDish(id);
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
        restaurant.setName(restaurantTo.getName());
        restaurantRepository.save(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "restaurant", allEntries = true)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody RestaurantTo restaurantTo) {
        checkNew(restaurantTo);
        val created = restaurantRepository.save(RestaurantUtil.createNewFromTo(restaurantTo));
        val uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}/menu-items/{menuItemId}")
    public MenuItem getMenuItem(@PathVariable int id, @PathVariable int menuItemId) {
        return super.getMenuItem(menuItemId, id);
    }

    @GetMapping("/{id}/menu-items")
    public List<MenuItem> getMenuItems(@PathVariable int id) {
        return super.getMenuItems(id);
    }

    @PostMapping("/{id}/menu-items")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MenuItem> createMenuItemWithLocation(@Valid @RequestBody MenuItemTo menuItemTo, @PathVariable int id) {
        checkNew(menuItemTo);
        val created = menuItemService.create(menuItemTo, id);
        val uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/menu-items/{menuItemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMenuItem(@Valid @RequestBody MenuItemTo menuItemTo, @PathVariable int id, @PathVariable int menuItemId) {
        assureIdConsistent(menuItemTo, menuItemId);
        menuItemService.update(menuItemTo, id);
    }
}

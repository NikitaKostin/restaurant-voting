package ru.javadiploma.restaurantvoting.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.to.MenuTo;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController extends AbstractRestaurantRestController {
    static final String REST_URL = "/rest/admin/restaurant";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        return super.getWithMenu(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRestaurant(@RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        super.updateRestaurant(restaurantTo, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant createRestaurant(@RequestBody RestaurantTo restaurantTo) {
        return super.createRestaurant(restaurantTo);
    }

    @GetMapping("/{id}/menus/{menuId}")
    public Menu getMenu(@PathVariable int id, @PathVariable int menuId) {
        return super.getMenu(menuId, id);

    } @GetMapping("/{id}/menus")
    public List<Menu> getMenus(@PathVariable int id) {
        return super.getMenus(id);
    }

    @PostMapping("/{id}/menus")
    public Menu createMenu(@RequestBody MenuTo menuTo, @PathVariable int id) {
        return super.createMenu(menuTo, id);
    }

    @PutMapping(value = "/{id}/menus/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMenu(@RequestBody MenuTo menuTo, @PathVariable int id,  @PathVariable int menuId) {
        super.updateMenu(menuTo, menuId, id);
    }

}

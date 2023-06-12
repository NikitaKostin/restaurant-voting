package ru.javadiploma.restaurantvoting.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController extends AbstractRestaurantRestController {
    static final String REST_URL = "/api/rest/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/with-menu-items")
    public Restaurant getWithMenuItems(@PathVariable int id) {
        return super.getWithMenuItems(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}/menu-items")
    public List<MenuItem> getMenuItems(@PathVariable int id) {
        return super.getMenuItems(id);
    }
}
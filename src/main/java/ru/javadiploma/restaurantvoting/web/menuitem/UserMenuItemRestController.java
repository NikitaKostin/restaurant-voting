package ru.javadiploma.restaurantvoting.web.menuitem;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javadiploma.restaurantvoting.model.MenuItem;

import java.util.List;

@RestController
@RequestMapping(value = UserMenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMenuItemRestController extends MenuItemRestController {
    static final String REST_URL = "/api/rest/restaurants";

    @GetMapping("/{id}/menu-items")
    public List<MenuItem> getMenuItems(@PathVariable int id) {
        return super.getMenuItems(id);
    }
}

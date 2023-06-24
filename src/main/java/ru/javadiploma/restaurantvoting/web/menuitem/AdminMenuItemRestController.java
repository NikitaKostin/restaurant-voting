package ru.javadiploma.restaurantvoting.web.menuitem;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.to.MenuItemTo;

import javax.validation.Valid;
import java.util.List;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuItemRestController extends MenuItemRestController {
    static final String REST_URL = "/api/rest/admin/restaurants";

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

    @DeleteMapping("/{id}/menu-items/{menuItemId}")
    public void deleteMenuItem(@PathVariable int id, @PathVariable int menuItemId) {
        super.delete(menuItemId, id);
    }
}

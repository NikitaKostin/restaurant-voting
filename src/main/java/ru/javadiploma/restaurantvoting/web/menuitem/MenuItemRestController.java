package ru.javadiploma.restaurantvoting.web.menuitem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javadiploma.restaurantvoting.model.MenuItem;
import ru.javadiploma.restaurantvoting.service.MenuItemService;

import java.util.List;

@Slf4j
public abstract class MenuItemRestController {
    @Autowired
    protected MenuItemService menuItemService;

    public List<MenuItem> getMenuItems(int id) {
        log.info("get menu items for restaurantId {}", id);
        return menuItemService.getAll(id);
    }

    public MenuItem getMenuItem(int menuItemId, int restaurantId) {
        log.info("get menu item with menuItemId {} for restaurantId {}", menuItemId, restaurantId);
        return menuItemService.get(menuItemId, restaurantId);
    }

    public void delete(int menuItemId, int restaurantId) {
        log.info("delete menu item with menuItemId {} for restaurantId {}", menuItemId, restaurantId);
        menuItemService.delete(menuItemId, restaurantId);
    }
}

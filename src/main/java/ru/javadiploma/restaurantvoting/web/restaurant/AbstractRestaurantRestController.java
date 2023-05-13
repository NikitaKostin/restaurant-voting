package ru.javadiploma.restaurantvoting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javadiploma.restaurantvoting.model.Menu;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.service.MenuService;
import ru.javadiploma.restaurantvoting.service.RestaurantService;
import ru.javadiploma.restaurantvoting.to.MenuTo;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;

import java.util.List;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNew;


public abstract class AbstractRestaurantRestController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    public Restaurant get(int id) {
        return restaurantService.get(id);
    }

    public Restaurant getWithMenu(int id) {
        return restaurantService.getWithMenu(id);
    }

    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    public void updateRestaurant(RestaurantTo restaurantTo, int id) {
        assureIdConsistent(restaurantTo, id);
        restaurantService.update(restaurantTo, id);
    }

    public Restaurant createRestaurant(RestaurantTo restaurantTo) {
        checkNew(restaurantTo);
        return restaurantService.create(restaurantTo);
    }

    public List<Menu> getMenus(int id) {
        return menuService.getAll(id);
    }

    public Menu getMenu(int id, int restaurantId) {
        return menuService.get(id, restaurantId);
    }

    public Menu createMenu(MenuTo menuTo, int restaurantId) {
        return menuService.create(menuTo, restaurantId);
    }

    public void updateMenu(MenuTo menuTo, int id, int restaurantId) {
        assureIdConsistent(menuTo, id);
        menuService.update(menuTo, id, restaurantId);
    }
}

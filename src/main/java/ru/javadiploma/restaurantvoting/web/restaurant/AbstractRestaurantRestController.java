package ru.javadiploma.restaurantvoting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javadiploma.restaurantvoting.error.ResourceNotFoundException;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class AbstractRestaurantRestController {
    @Autowired
    protected RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant with id " + id + " not found")
        );
    }

    public Restaurant getRestaurantMenuItemsWithDish(int id) {
        log.info("get restaurant menu items {} with dish", id);
        return restaurantRepository.getRestaurantMenuItemsWithDishByDate(id, LocalDate.now()).orElseThrow(
                () -> new ResourceNotFoundException("Not found today menu item for restaurant with id " + id)
        );
    }

    public List<Restaurant> getAll() {
        log.info("get all");
        return restaurantRepository.findAll();
    }

    public void deleteById(int id) {
        log.info("delete {}", id);
        restaurantRepository.delete(restaurantRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Restaurant with id " + id + " not found")
                )
        );
    }
}

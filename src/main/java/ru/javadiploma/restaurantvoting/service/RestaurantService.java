package ru.javadiploma.restaurantvoting.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Restaurant;
import ru.javadiploma.restaurantvoting.repository.RestaurantRepository;
import ru.javadiploma.restaurantvoting.to.RestaurantTo;
import ru.javadiploma.restaurantvoting.util.RestaurantUtil;

import java.util.List;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    public Restaurant getWithMenu(int id) {
        return checkNotFoundWithId(restaurantRepository.getWithRestaurant(id).orElse(null), id);
    }

    @Cacheable("restaurant")
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    @Transactional
    public void update(RestaurantTo restaurantTo, int id) {
        assureIdConsistent(restaurantTo, id);
        Restaurant restaurant = get(restaurantTo.id());
        RestaurantUtil.updateFromTo(restaurant, restaurantTo);
        restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurant", allEntries = true)
    public Restaurant create(RestaurantTo restaurantTo) {
        return restaurantRepository.save(RestaurantUtil.createNewFromTo(restaurantTo));
    }
}

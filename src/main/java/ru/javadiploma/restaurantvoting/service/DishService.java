package ru.javadiploma.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.to.DishTo;
import ru.javadiploma.restaurantvoting.util.DishUtil;

import java.util.List;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.findById(id).orElse(null), id);
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish create(DishTo dishTo) {
        return dishRepository.save(DishUtil.createNewFromTo(dishTo));
    }

    @Transactional
    public void update(DishTo dishTo, int id) {
        assureIdConsistent(dishTo, id);
        Dish dish = get(dishTo.id());
        DishUtil.updateFromTo(dish, dishTo);
        dishRepository.save(dish);
    }
}

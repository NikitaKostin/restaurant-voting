package ru.javadiploma.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.to.DishTo;
import ru.javadiploma.restaurantvoting.util.DishUtil;

import javax.transaction.Transactional;
import java.util.List;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;

@Service
public class DishService {
    @Autowired
    protected DishRepository dishRepository;

    public Dish get(int id) {
        return dishRepository.findById(id).orElse(null);
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

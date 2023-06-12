package ru.javadiploma.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.to.DishTo;
import ru.javadiploma.restaurantvoting.util.DishUtil;

import java.util.List;

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

}

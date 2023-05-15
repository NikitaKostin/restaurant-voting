package ru.javadiploma.restaurantvoting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.repository.DishRepository;
import ru.javadiploma.restaurantvoting.to.DishTo;
import ru.javadiploma.restaurantvoting.util.DishUtil;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.List;

import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    static final String REST_URL = "/api/rest/admin/dish";

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return dishRepository.findById(id).orElse(null);
    }

    @GetMapping("/")
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        assureIdConsistent(dishTo, id);
        Dish dish = get(dishTo.id());
        DishUtil.updateFromTo(dish, dishTo);
        dishRepository.save(dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Dish create(@Valid @RequestBody DishTo dishTo) {
        checkNew(dishTo);
        return dishRepository.save(DishUtil.createNewFromTo(dishTo));
    }
}

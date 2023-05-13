package ru.javadiploma.restaurantvoting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.model.Dish;
import ru.javadiploma.restaurantvoting.service.DishService;
import ru.javadiploma.restaurantvoting.to.DishTo;

import javax.validation.Valid;
import java.util.List;

import static ru.javadiploma.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.javadiploma.restaurantvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    static final String REST_URL = "/rest/admin/dish";

    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return dishService.get(id);
    }

    @GetMapping("/")
    public List<Dish> getAll() {
        return dishService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        assureIdConsistent(dishTo, id);
        dishService.update(dishTo, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Dish create(@Valid @RequestBody DishTo dishTo) {
        checkNew(dishTo);
        return dishService.create(dishTo);
    }
}

package ru.javadiploma.restaurantvoting.to;

import javax.validation.constraints.NotNull;

public class MenuTo extends BaseTo {
    @NotNull
    protected int dishId;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }


    public MenuTo() {
    }

    public MenuTo(Integer id, int dishId) {
        super(id);
        this.dishId = dishId;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", dishId=" + dishId +
                '}';
    }
}

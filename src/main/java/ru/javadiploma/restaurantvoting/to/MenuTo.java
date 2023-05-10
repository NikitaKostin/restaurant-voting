package ru.javadiploma.restaurantvoting.to;

import javax.validation.constraints.NotNull;

public class MenuTo extends BaseTo {
    @NotNull
    protected int dishId;

    @NotNull
    private int restaurantId;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuTo() {
    }

    public MenuTo(Integer id, int dishId, int restaurantId) {
        super(id);
        this.dishId = dishId;
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}

package ru.javadiploma.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu",  uniqueConstraints = {@UniqueConstraint(columnNames = {"dish_id", "restaurant_id", "create_date"}, name = "menu_unique_dish_restaurant_create_date_idx")})
public class Menu extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @NotNull
    protected Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    @Column(name = "create_date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    private LocalDate createDate;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Menu() {
    }

    public Menu(Integer id, Dish dish, Restaurant restaurant, LocalDate createDate) {
        super(id);
        this.dish = dish;
        this.restaurant = restaurant;
        this.createDate = createDate;
    }

    public Menu(Integer id, LocalDate createDate) {
        super(id);
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", createDate=" + createDate +
                '}';
    }
}

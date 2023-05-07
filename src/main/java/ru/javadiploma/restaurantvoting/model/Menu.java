package ru.javadiploma.restaurantvoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu",  uniqueConstraints = {@UniqueConstraint(columnNames = {"dish_id", "restaurant_id", "create_date"}, name = "menu_unique_dish_restaurant_create_date_idx")})
public class Menu extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @NotNull
    protected Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
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

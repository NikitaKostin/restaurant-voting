package ru.javadiploma.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu",  uniqueConstraints = {@UniqueConstraint(columnNames = {"dish_id", "restaurant_id", "create_date"}, name = "menu_unique_dish_restaurant_create_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"restaurant"})
public class Menu extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @NotNull
    protected Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    @NotNull
    private Restaurant restaurant;

    @Column(name = "create_date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    private LocalDate createDate;

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
}

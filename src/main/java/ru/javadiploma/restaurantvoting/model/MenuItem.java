package ru.javadiploma.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_item",  uniqueConstraints = {@UniqueConstraint(columnNames = {"dish_id", "restaurant_id", "offer_date"}, name = "menu_unique_dish_restaurant_offer_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @NotNull
    @ToString.Exclude
    protected Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;

    @Column(name = "offer_date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    private LocalDate offerDate;

    public MenuItem(Integer id, Dish dish, Restaurant restaurant, LocalDate offerDate) {
        super(id);
        this.dish = dish;
        this.restaurant = restaurant;
        this.offerDate = offerDate;
    }

    public MenuItem(Integer id, LocalDate offerDate) {
        this(id, null, null, offerDate);
    }
}

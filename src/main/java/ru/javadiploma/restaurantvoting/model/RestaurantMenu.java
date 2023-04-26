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
@Table(name = "restaurant_menu",  uniqueConstraints = {@UniqueConstraint(columnNames = {"dish_name", "price", "restaurant_id", "create_date"}, name = "restaurant_menu_unique_dish_name_price_restaurant_create_date_idx")})
public class RestaurantMenu extends AbstractBaseEntity {
    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "dish_name", nullable = false)
    protected String dishName;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 10000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "create_date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    private LocalDate createDate;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public RestaurantMenu() {
    }

    public RestaurantMenu(Integer id, String dishName, int price, LocalDate createDate) {
        super(id);
        this.dishName = dishName;
        this.price = price;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "RestaurantMenu{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                '}';
    }
}

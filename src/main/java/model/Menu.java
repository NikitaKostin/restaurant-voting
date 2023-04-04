package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu",  uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id", "create_date_time"}, name = "menu_unique_name_restaurant_create_date_time_idx")})
public class Menu extends AbstractNamedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @Column(name = "create_date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime createDateTime;

    public Menu() {
    }

    public Menu(Menu m) {
        this(m.id, m.name, m.restaurant, m.createDateTime);
    }

    public Menu(Integer id, String name, Restaurant restaurant, LocalDateTime createDateTime) {
        super(id, name);
        this.restaurant = restaurant;
        this.createDateTime = createDateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}

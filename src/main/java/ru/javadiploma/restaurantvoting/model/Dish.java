package ru.javadiploma.restaurantvoting.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dish",  uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "price"}, name = "dish_unique_name_price_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Dish extends NamedEntity {
    @Column(name = "price", nullable = false)
    @Range(min = 1)
    private int price;

    public Dish(Dish d) {
        this(d.id, d.name, d.price);
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }
}

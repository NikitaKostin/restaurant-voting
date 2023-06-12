package ru.javadiploma.restaurantvoting.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant",  uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("id")
    @ApiModelProperty(hidden = true)
    @ToString.Exclude
    private List<MenuItem> menuItems;

    public Restaurant(Restaurant r) {
        this(r.id, r.name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}

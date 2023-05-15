package ru.javadiploma.restaurantvoting.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DishTo extends BaseTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Range(min = 10, max = 1000000)
    private int price;

    public DishTo(Integer id, String name, int price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
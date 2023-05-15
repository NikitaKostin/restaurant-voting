package ru.javadiploma.restaurantvoting.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RestaurantTo extends BaseTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }
}

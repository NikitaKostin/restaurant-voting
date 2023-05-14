package ru.javadiploma.restaurantvoting.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MenuTo extends BaseTo {
    @NotNull
    protected int dishId;

    public MenuTo(Integer id, int dishId) {
        super(id);
        this.dishId = dishId;
    }
}

package ru.javadiploma.restaurantvoting.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DishTo extends BaseTo {
    @Range(min = 10, max = 1000000)
    private int price;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    public DishTo() {
    }

    public DishTo(Integer id, int price, String name) {
        super(id);
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}

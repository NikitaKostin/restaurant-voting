package ru.javadiploma.restaurantvoting.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

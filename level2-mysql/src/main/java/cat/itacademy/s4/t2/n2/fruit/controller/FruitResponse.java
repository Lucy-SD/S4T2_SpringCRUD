package cat.itacademy.s4.t2.n2.fruit.controller;

import cat.itacademy.s4.t2.n2.fruit.model.Fruit;

import java.util.UUID;

public record FruitResponse(
        Long id,
        String name,
        double weight
) {
    public static FruitResponse from(Fruit fruit) {
        return new FruitResponse(
                fruit.getId(),
                fruit.getName(),
                fruit.getWeight()
        );
    }
}

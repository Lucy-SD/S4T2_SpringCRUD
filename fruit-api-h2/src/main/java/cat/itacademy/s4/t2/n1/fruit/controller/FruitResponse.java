package cat.itacademy.s4.t2.n1.fruit.controller;

import cat.itacademy.s4.t2.n1.fruit.model.Fruit;

import java.util.UUID;

public record FruitResponse(
        UUID id,
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

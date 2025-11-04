package cat.itacademy.s4.t2.n3.fruit.controller;

import cat.itacademy.s4.t2.n3.fruit.model.Fruit;

public record FruitResponse(
        String id,
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

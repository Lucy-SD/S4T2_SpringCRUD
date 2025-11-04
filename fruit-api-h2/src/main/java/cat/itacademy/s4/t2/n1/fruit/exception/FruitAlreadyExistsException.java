package cat.itacademy.s4.t2.n1.fruit.exception;

import cat.itacademy.s4.t2.n1.fruit.model.Fruit;

public class FruitAlreadyExistsException extends RuntimeException {
    public FruitAlreadyExistsException(Fruit fruit) {
        super("La fruta con nombre: " + fruit.getName() + " ya existe.");
    }
}

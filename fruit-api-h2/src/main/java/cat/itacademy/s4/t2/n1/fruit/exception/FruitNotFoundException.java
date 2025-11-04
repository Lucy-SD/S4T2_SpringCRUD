package cat.itacademy.s4.t2.n1.fruit.exception;

import java.util.UUID;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(UUID id) {
        super("No se encontró la fruta con ID: " + id + ".");
    }
}

package cat.itacademy.s4.t2.n3.fruit.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(String id) {
        super("No se encontró la fruta con ID: " + id + ".");
    }
}

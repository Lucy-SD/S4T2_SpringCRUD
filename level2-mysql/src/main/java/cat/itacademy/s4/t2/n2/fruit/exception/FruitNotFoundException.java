package cat.itacademy.s4.t2.n2.fruit.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(Long id) {
        super("No se encontró la fruta con ID: " + id + ".");
    }
}

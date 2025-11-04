package cat.itacademy.s4.t2.n1.fruit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FruitNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFruitNotFoundException(FruitNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(FruitAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFruitAlreadyExistsException(FruitAlreadyExistsException ex) {
        return ex.getMessage();
    }

}

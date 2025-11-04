package cat.itacademy.s4.t2.n3.fruit.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateFruitRequest(
        @NotBlank(message = "El nombre de la fruta es obligatorio.")
        String name,

        @Positive(message = "El peso de la fruta debe ser positivo.")
        double weight
) {
}

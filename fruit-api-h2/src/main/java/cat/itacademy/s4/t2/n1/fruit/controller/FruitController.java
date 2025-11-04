package cat.itacademy.s4.t2.n1.fruit.controller;

import cat.itacademy.s4.t2.n1.fruit.model.Fruit;
import cat.itacademy.s4.t2.n1.fruit.service.FruitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping
    public List<FruitResponse> getFruits() {
        return fruitService.getFruits().stream()
                .map(FruitResponse::from)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FruitResponse createFruit(@Valid @RequestBody CreateFruitRequest fruitRequest) {
        Fruit fruit = new Fruit(null, fruitRequest.name(), fruitRequest.weight());
        Fruit savedFruit = fruitService.createFruit(fruit);
        return FruitResponse.from(savedFruit);
    }

    @GetMapping("/{id}")
    public FruitResponse getFruitById(@PathVariable UUID id) {
        Fruit fruit = fruitService.getFruitById(id);
        return FruitResponse.from(fruit);
    }

    @PutMapping("/{id}")
    public FruitResponse updateFruit(@PathVariable UUID id, @Valid @RequestBody CreateFruitRequest fruitRequest) {
        Fruit existingFruit = fruitService.getFruitById(id);

        existingFruit.setName(fruitRequest.name());
        existingFruit.setWeight(fruitRequest.weight());

        Fruit updatedFruit = fruitService.updateFruit(existingFruit);

        return FruitResponse.from(updatedFruit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFruitById(@PathVariable UUID id) {
        fruitService.deleteFruitById(id);
    }
}

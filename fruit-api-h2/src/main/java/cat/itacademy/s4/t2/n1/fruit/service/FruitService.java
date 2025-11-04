package cat.itacademy.s4.t2.n1.fruit.service;

import cat.itacademy.s4.t2.n1.fruit.exception.FruitAlreadyExistsException;
import cat.itacademy.s4.t2.n1.fruit.exception.FruitNotFoundException;
import cat.itacademy.s4.t2.n1.fruit.model.Fruit;
import cat.itacademy.s4.t2.n1.fruit.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    private void validateWeight(Fruit fruit) {
        if (fruit.getWeight() <= 0) {
            throw new IllegalArgumentException("Una Fruta no puede tener peso negativo.");
        }
    }

    public Fruit createFruit(Fruit fruit) {
        validateWeight(fruit);
        if (fruitRepository.existsByName(fruit.getName())) {
            throw new FruitAlreadyExistsException(fruit);
        }

        return fruitRepository.save(fruit);
    }

    public List<Fruit> getFruits() {
        return fruitRepository.findAll();
    }

    public Fruit getFruitById(UUID id) {
        return fruitRepository.findById(id).
                orElseThrow(() -> new FruitNotFoundException(id));
    }

    public List<Fruit> getFruitByName(String name) {
        return fruitRepository.findByName(name);
    }

    public Fruit updateFruit(Fruit fruitToUpdate) {
        validateWeight(fruitToUpdate);

        Fruit existinFruit = getFruitById(fruitToUpdate.getId());
        existinFruit.setName(fruitToUpdate.getName());
        existinFruit.setWeight(fruitToUpdate.getWeight());
        return fruitRepository.save(existinFruit);
    }

    public void deleteFruitById(UUID id) {
        Fruit existingFruit = getFruitById(id);
        fruitRepository.deleteById(existingFruit.getId());
    }

    public void deleteAll() {
        fruitRepository.deleteAll();
    }
}

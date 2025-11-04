package cat.itacademy.s4.t2.n3.fruit.service;

import cat.itacademy.s4.t2.n3.fruit.exception.FruitAlreadyExistsException;
import cat.itacademy.s4.t2.n3.fruit.exception.FruitNotFoundException;
import cat.itacademy.s4.t2.n3.fruit.model.Fruit;
import cat.itacademy.s4.t2.n3.fruit.repository.MongoFruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    private final MongoFruitRepository fruitRepository;

    public FruitService(MongoFruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Fruit createFruit(Fruit fruit) {
        if (fruitRepository.existsByName(fruit.getName())) {
            throw new FruitAlreadyExistsException(fruit);
        }
        return fruitRepository.save(fruit);
    }

    public List<Fruit> getFruits() {
        return fruitRepository.findAll();
    }

    public Fruit getFruitById(String id) {
        return fruitRepository.findById(id).
                orElseThrow(() -> new FruitNotFoundException(id));
    }

    public List<Fruit> getFruitByName(String name) {
        return fruitRepository.findByName(name);
    }

    public Fruit updateFruit(Fruit fruitToUpdate) {
        Fruit existinFruit = getFruitById(fruitToUpdate.getId());
        existinFruit.setName(fruitToUpdate.getName());
        existinFruit.setWeight(fruitToUpdate.getWeight());
        return fruitRepository.save(existinFruit);
    }

    public void deleteFruitById(String id) {
        Fruit existingFruit = getFruitById(id);
        fruitRepository.deleteById(existingFruit.getId());
    }

    public void deleteAll() {
        fruitRepository.deleteAll();
    }
}

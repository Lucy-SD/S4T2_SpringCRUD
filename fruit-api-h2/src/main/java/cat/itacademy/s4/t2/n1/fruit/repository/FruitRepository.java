package cat.itacademy.s4.t2.n1.fruit.repository;

import cat.itacademy.s4.t2.n1.fruit.model.Fruit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FruitRepository {

    Fruit save (Fruit fruit);
    List<Fruit> findAll();
    Optional<Fruit> findById(UUID id);
    List<Fruit> findByName(String name);
    boolean existsByName(String name);
    void deleteById(UUID id);
    void deleteAll();
}

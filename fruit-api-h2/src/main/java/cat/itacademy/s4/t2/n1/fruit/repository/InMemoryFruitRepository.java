package cat.itacademy.s4.t2.n1.fruit.repository;

import cat.itacademy.s4.t2.n1.fruit.model.Fruit;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryFruitRepository implements FruitRepository {

    private final Map<UUID, Fruit> fruits = new HashMap<>();


    @Override
    public Fruit save(Fruit fruit) {
        if (fruit.getId() == null) {
            fruit.setId(UUID.randomUUID());
        }
        fruits.put(fruit.getId(), fruit);
        return fruit;
    }

    @Override
    public List<Fruit> findAll() {
        return new ArrayList<>(fruits.values());
    }

    @Override
    public Optional<Fruit> findById(UUID id) {
        return Optional.ofNullable(fruits.get(id));
    }

    @Override
    public List<Fruit> findByName(String name) {
        if(name == null || name.isBlank()) {
            return new ArrayList<>(fruits.values());
        }
        return fruits.values().stream()
                .filter(fruit -> fruit.getName().toLowerCase()
                        .contains(name.toLowerCase())).toList();
    }

    @Override
    public boolean existsByName(String name) {
        if (name == null || name.isBlank()) return false;
        return fruits.values().stream()
                .anyMatch(fruit -> fruit.getName().equalsIgnoreCase(name));
    }

    @Override
    public void deleteById(UUID id) {
       fruits.remove(id);
    }

    @Override
    public void deleteAll() {
        fruits.clear();
    }
}

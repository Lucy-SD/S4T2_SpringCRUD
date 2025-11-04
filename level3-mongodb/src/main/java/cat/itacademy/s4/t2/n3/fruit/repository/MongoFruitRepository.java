package cat.itacademy.s4.t2.n3.fruit.repository;

import cat.itacademy.s4.t2.n3.fruit.model.Fruit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoFruitRepository extends MongoRepository<Fruit, String> {

    List<Fruit> findByName(String name);
    boolean existsByName(String name);
}

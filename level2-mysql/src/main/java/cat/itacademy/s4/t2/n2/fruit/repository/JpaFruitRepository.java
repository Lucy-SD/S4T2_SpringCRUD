package cat.itacademy.s4.t2.n2.fruit.repository;

import cat.itacademy.s4.t2.n2.fruit.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaFruitRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findByName(String name);
    boolean existsByName(String name);
}

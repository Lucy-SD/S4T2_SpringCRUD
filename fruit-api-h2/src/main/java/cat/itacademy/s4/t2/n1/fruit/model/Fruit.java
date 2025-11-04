package cat.itacademy.s4.t2.n1.fruit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity

public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private double weight;

    public Fruit() {}

    public Fruit(UUID id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public UUID getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public double getWeight() {
        return this.weight;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

}

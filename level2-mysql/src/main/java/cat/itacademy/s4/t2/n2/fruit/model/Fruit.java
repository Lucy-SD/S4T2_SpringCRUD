package cat.itacademy.s4.t2.n2.fruit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fruit")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double weight;

    public Fruit() {}

    public Fruit(Long id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Fruit(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public double getWeight() {
        return this.weight;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

}

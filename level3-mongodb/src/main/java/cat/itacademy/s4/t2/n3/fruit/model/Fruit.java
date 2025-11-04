package cat.itacademy.s4.t2.n3.fruit.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fruits")
public class Fruit {

    @Id
    private String id;

    private String name;
    private double weight;

    public Fruit() {}

    public Fruit(String id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Fruit(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public double getWeight() {
        return this.weight;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

}

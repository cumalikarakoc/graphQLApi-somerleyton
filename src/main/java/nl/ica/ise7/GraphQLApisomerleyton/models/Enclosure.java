package nl.ica.ise7.GraphQLApisomerleyton.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Enclosure {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "enclosure_num")
    private Long enclosureNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "animal_enclosure",
            joinColumns = @JoinColumn(name = "enclosure_num"),
            inverseJoinColumns = @JoinColumn(name = "animal_id"))
    private List<Animal> animals;

    public Enclosure() {
    }

    public Enclosure(Long enclosureNumber) {
        this.enclosureNumber = enclosureNumber;
    }

    public Long getEnclosureNumber() {
        return enclosureNumber;
    }

    public void setEnclosureNumber(Long enclosureNumber) {
        this.enclosureNumber = enclosureNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enclosure enclosure = (Enclosure) o;

        return enclosureNumber.equals(enclosure.enclosureNumber);
    }

    @Override
    public int hashCode() {
        return enclosureNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Enclosure{" +
                "enclosureNumber=" + enclosureNumber +
                '}';
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
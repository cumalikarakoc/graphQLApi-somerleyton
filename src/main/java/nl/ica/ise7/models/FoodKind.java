package nl.ica.ise7.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "FoodKind")
@Table(name = "food_kind")
public class FoodKind {

    @Id
    @Column(name = "food_type")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "supplies_food_type",
            joinColumns = @JoinColumn(name = "food_type"),
            inverseJoinColumns = @JoinColumn(name = "supplier_name")
    )
    private Set<Supplier> suppliers;

    @OneToMany(targetEntity = FoodStock.class, mappedBy = "foodType", fetch = FetchType.EAGER)
    private List<FoodStock> foodStocks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}

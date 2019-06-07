package nl.ica.ise7.models;

import javax.persistence.*;
import java.util.Set;

@Entity
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

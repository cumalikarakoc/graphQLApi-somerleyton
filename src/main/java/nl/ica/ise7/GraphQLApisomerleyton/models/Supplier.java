package nl.ica.ise7.GraphQLApisomerleyton.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Supplier {

    @Id
    @Column(name = "supplier_name")
    private String name;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "address")
    private String address;

    @ManyToMany(mappedBy = "suppliers", fetch = FetchType.EAGER)
    private Set<FoodKind> foodKinds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<FoodKind> getFoodKinds() {
        return foodKinds;
    }

    public void setFoodKinds(Set<FoodKind> foodKinds) {
        this.foodKinds = foodKinds;
    }
}

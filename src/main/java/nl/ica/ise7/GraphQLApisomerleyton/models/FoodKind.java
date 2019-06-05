package nl.ica.ise7.GraphQLApisomerleyton.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_kind")
public class FoodKind {

    @Id
    @Column(name = "food_type")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

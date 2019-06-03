package nl.ica.ise7.GraphQLApisomerleyton.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Keeper {
    @Id
    @Column(name = "keeper_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

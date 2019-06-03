package nl.ica.ise7.GraphQLApisomerleyton.models;

import javax.persistence.*;

@Entity
public class Area {
    @Id
    @Column(name = "area_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "headkeeper", referencedColumnName = "keeper_name")
    private Keeper headKeeper;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Keeper getHeadKeeper() {
        return headKeeper;
    }

    public void setHeadKeeper(Keeper headKeeper) {
        this.headKeeper = headKeeper;
    }
}

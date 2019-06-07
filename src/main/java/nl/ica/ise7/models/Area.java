package nl.ica.ise7.models;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(targetEntity = FoodStock.class, mappedBy = "areaName")
    private List<FoodStock> foodStocks;
}

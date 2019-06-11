package nl.ica.ise7.models;


import nl.ica.ise7.models.compositeKeys.FoodStockIdentity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@IdClass(FoodStockIdentity.class)
@Table(name = "stock")
public class FoodStock implements Serializable {


    @Id
    @Column(name = "food_type")
    private String foodType;

    @Id
    @NotNull
    @Column(name = "area_name")
    private String areaName;

    @Column(name = "amount", precision = 5, scale = 3)
    private float amount;

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

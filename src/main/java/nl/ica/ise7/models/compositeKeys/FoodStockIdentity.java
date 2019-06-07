package nl.ica.ise7.models.compositeKeys;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class FoodStockIdentity implements Serializable {

    @NotNull
    @Column(name = "food_type")
    private String foodType;

    @NotNull
    @Column(name = "area_name")
    private String areaName;

    public FoodStockIdentity() {
    }

    public FoodStockIdentity(String foodType, String areaName) {
        this.foodType = foodType;
        this.areaName = areaName;
    }

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
}

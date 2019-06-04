package nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class EnclosureIdentity implements Serializable {

    @NotNull
    @Column(name = "area_name")
    private String areaName;

    @NotNull
    @Column(name = "enclosure_num")
    private int enclosureNumber;

    public int getEnclosureNumber() {
        return enclosureNumber;
    }

    public void setEnclosureNumber(int enclosureNumber) {
        this.enclosureNumber = enclosureNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

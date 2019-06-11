package nl.ica.ise7.models;

import nl.ica.ise7.models.compositeKeys.AnimalEnclosureIdentity;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "animal_enclosure")
public class AnimalEnclosure implements Persistable {
    @EmbeddedId
    private AnimalEnclosureIdentity identity;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "enclosure_num")
    private int enclosureNumber;

    @Column(name = "end_date")
    private Date endDate;

    public AnimalEnclosureIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(AnimalEnclosureIdentity identity) {
        this.identity = identity;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getEnclosureNumber() {
        return enclosureNumber;
    }

    public void setEnclosureNumber(int enclosureNumber) {
        this.enclosureNumber = enclosureNumber;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Serializable getId() {
        return getIdentity();
    }

    @Override
    public boolean isNew() {
        return true;
    }
}

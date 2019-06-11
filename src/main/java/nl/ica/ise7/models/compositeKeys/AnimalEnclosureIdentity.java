package nl.ica.ise7.models.compositeKeys;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class AnimalEnclosureIdentity implements Serializable {
    @NotNull
    @Column(name = "animal_id")
    private String animalId;

    @NotNull
    private Date since;

    public AnimalEnclosureIdentity() {
    }

    public AnimalEnclosureIdentity(String animalId, Date since) {
        this.animalId = animalId;
        this.since = since;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}

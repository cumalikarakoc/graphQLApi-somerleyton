package nl.ica.ise7.models;

import javax.persistence.*;

@Entity
public class Species {
    @Id
    @Column(name = "species_name")
    private String speciesName;

    @Column(name = "description")
    private String description;

    @Column(name = "family")
    private String family;

    @Column(name = "species")
    private String species;

    @Column(name = "subspecies")
    private String subspecies;

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSubspecies() {
        return subspecies;
    }

    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }
}

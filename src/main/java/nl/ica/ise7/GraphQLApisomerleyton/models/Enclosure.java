package nl.ica.ise7.GraphQLApisomerleyton.models;

import nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys.EnclosureIdentity;

import javax.persistence.*;

@Entity
public class Enclosure {

    @EmbeddedId
    private EnclosureIdentity enclosureIdentity;

    public EnclosureIdentity getEnclosureIdentity() {
        return enclosureIdentity;
    }

    public void setEnclosureIdentity(EnclosureIdentity enclosureIdentity) {
        this.enclosureIdentity = enclosureIdentity;
    }
}

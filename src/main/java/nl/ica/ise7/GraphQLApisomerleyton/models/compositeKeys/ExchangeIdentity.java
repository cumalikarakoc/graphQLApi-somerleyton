package nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ExchangeIdentity implements Serializable {
    @NotNull
    @Column(name = "animal_id")
    private String animalId;

    @NotNull
    @Column(name = "exchange_date")
    private Date exchangeDate;

    public ExchangeIdentity() {}

    public ExchangeIdentity(String animalId, Date exchangeDate) {
        this.animalId = animalId;
        this.exchangeDate = exchangeDate;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}

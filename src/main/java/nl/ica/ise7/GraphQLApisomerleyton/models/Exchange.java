package nl.ica.ise7.GraphQLApisomerleyton.models;

import nl.ica.ise7.GraphQLApisomerleyton.models.compositeKeys.ExchangeIdentity;
import org.springframework.data.domain.Persistable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Exchange implements Persistable {

    @EmbeddedId
    private ExchangeIdentity identity;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "place")
    private String place;

    @Transient
    private Boolean isNew;

    public ExchangeIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(ExchangeIdentity identity) {
        this.identity = identity;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getExchangeDate(){
        return identity.getExchangeDate();
    }

    public void setIsNew(Boolean isNew){
        this.isNew = isNew;
    }

    @Override
    public Serializable getId() {
        return identity;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}

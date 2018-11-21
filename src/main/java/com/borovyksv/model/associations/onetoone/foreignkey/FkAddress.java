package com.borovyksv.model.associations.onetoone.foreignkey;

import com.borovyksv.model.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class FkAddress {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    protected String street;
    protected String zipcode;
    protected String city;

    public FkAddress(String street, String zipcode, String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }
}

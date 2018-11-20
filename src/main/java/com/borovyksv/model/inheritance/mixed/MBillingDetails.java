package com.borovyksv.model.inheritance.mixed;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MBillingDetails {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    protected String owner;
}
/**
 * EXAMPLE:
 * select
 *      ID, OWNER, ACCOUNT, BANKNAME, SWIFT,
 *      EXPMONTH, EXPYEAR, CARDNUMBER,
 *      BD_TYPE
 * from
 *      BILLINGDETAILS
 *      left outer join CREDITCARD on ID=CREDITCARD_ID
 *      */
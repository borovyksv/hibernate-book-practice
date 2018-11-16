package com.borovyksv.model.inheritance.associations.onetomany;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
public class OtmCreditCard extends OtmBillingDetails {

    @NotNull private String cardNumber;
    @NotNull private String expMonth;
    @NotNull private String expYear;

    @Builder
    public OtmCreditCard(Long id, String owner, OtmUser otmUser, String cardNumber, String expMonth, String expYear) {
        super(id, owner, otmUser);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}

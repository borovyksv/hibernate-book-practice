package com.borovyksv.model.inheritance.associations.manytoone;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
public class MtoCreditCard extends MtoBillingDetails {

    @NotNull private String cardNumber;
    @NotNull private String expMonth;
    @NotNull private String expYear;

    @Builder
    public MtoCreditCard(Long id, String owner, String cardNumber, String expMonth, String expYear) {
        super(id, owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}

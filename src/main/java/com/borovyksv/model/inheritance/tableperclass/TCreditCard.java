package com.borovyksv.model.inheritance.tableperclass;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
public class TCreditCard extends TBillingDetails {

    @NotNull private String cardNumber;
    @NotNull private String expMonth;
    @NotNull private String expYear;

    @Builder
    public TCreditCard(Long id, String owner, String cardNumber, String expMonth, String expYear) {
        super(id, owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}

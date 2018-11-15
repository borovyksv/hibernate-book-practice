package com.borovyksv.model.part2Chapter6Inheritance;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import static com.borovyksv.model.part2Chapter6Inheritance.BillingDetails.OWNER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@AttributeOverride(name = OWNER, column = @Column(name = "CC_" + OWNER, nullable = false))
public class CreditCard extends BillingDetails {

    @NotNull private String cardNumber;
    @NotNull private String expMonth;
    @NotNull private String expYear;

    @Builder
    public CreditCard(Long id, String owner, String cardNumber, String expMonth, String expYear) {
        super(id, owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}

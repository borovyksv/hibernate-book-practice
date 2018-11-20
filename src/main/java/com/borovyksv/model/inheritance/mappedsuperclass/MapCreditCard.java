package com.borovyksv.model.inheritance.mappedsuperclass;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import static com.borovyksv.model.inheritance.mappedsuperclass.MapBillingDetails.OWNER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@AttributeOverride(name = OWNER, column = @Column(name = "CC_" + OWNER, nullable = false))
public class MapCreditCard extends MapBillingDetails {

    @NotNull private String cardNumber;
    @NotNull private String expMonth;
    @NotNull private String expYear;

    @Builder
    public MapCreditCard(Long id, String owner, String cardNumber, String expMonth, String expYear) {
        super(id, owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}

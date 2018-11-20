package com.borovyksv.model.inheritance.mixed;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Entity
@DiscriminatorValue("sec_cc")
@SecondaryTable(name = "sec_credit_card", pkJoinColumns = @PrimaryKeyJoinColumn(name = "sec_credit_card_id"))
public class MCreditCard extends MBillingDetails {

    @NotNull
    @Column(table = "sec_credit_card", nullable = false)
    private String cardNumber;

    @NotNull
    @Column(table = "sec_credit_card", nullable = false)
    private String expMonth;

    @NotNull
    @Column(table = "sec_credit_card", nullable = false)
    private String expYear;

    @Builder
    public MCreditCard(Long id, String owner, String cardNumber, String expMonth, String expYear) {
        super(id, owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}

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
public class OtmBankAccount extends OtmBillingDetails {

    @NotNull private String account;
    @NotNull private String bankName;
    @NotNull private String swift;

    @Builder
    public OtmBankAccount(Long id, String owner, OtmUser otmUser, String account, String bankName, String swift) {
        super(id, owner, otmUser);
        this.account = account;
        this.bankName = bankName;
        this.swift = swift;
    }
}

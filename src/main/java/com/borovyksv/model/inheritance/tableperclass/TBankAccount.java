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
public class TBankAccount extends TBillingDetails {

    @NotNull private String account;
    @NotNull private String bankName;
    @NotNull private String swift;

    @Builder
    public TBankAccount(Long id, String owner, String account, String bankName, String swift) {
        super(id, owner);
        this.account = account;
        this.bankName = bankName;
        this.swift = swift;
    }
}

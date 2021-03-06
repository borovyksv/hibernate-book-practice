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
@AttributeOverride(name = OWNER, column = @Column(name = "BA_" + OWNER, nullable = false))
public class MapBankAccount extends MapBillingDetails {

    @NotNull private String account;
    @NotNull private String bankName;
    @NotNull private String swift;

    @Builder
    public MapBankAccount(Long id, String owner, String account, String bankName, String swift) {
        super(id, owner);
        this.account = account;
        this.bankName = bankName;
        this.swift = swift;
    }
}

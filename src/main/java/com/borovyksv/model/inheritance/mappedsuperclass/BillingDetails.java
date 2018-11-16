package com.borovyksv.model.inheritance.mappedsuperclass;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class BillingDetails {
    public static final String OWNER = "owner";

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    protected String owner;
}

/**
 * EXAMPLE:
 * select
 *      ID, OWNER, ACCOUNT, BANKNAME, SWIFT
 * from
 *      BANKACCOUNT;
 *
 * select
 *      ID, CC_OWNER, CARDNUMBER, EXPMONTH, EXPYEAR
 * from
 *      CREDITCARD;
 * */
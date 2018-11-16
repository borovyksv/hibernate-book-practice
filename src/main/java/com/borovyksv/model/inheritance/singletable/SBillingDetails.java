package com.borovyksv.model.inheritance.singletable;

import com.borovyksv.model.Constants;
import lombok.*;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "bd_type") // adds extra column "bd_type" (JPA & HIBERNATE)!
@DiscriminatorFormula("case when account is not null then 'SBankAccount' else 'SCreditCard' end") // dynamic polymorphism (HIBERNATE Specific)!
public abstract class SBillingDetails {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    @Column(nullable = false)
    protected String owner;
}
/**
 * EXAMPLE:
 * select
 *      ID, OWNER, EXPMONTH, EXPYEAR, CARDNUMBER,
 *      ACCOUNT, BANKNAME, SWIFT, BD_TYPE
 * from
 *      BILLINGDETAILS
 *
 ****** To query the CreditCard subclass, Hibernate adds a restriction on the discriminator column: *****
 *
 * select
 *      ID, OWNER, EXPMONTH, EXPYEAR, CARDNUMBER
 * from
 *      BILLINGDETAILS
 * where
 *      BD_TYPE='CC'
 * */
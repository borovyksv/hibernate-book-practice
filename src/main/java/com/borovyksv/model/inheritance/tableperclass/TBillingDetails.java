package com.borovyksv.model.inheritance.tableperclass;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TBillingDetails {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    protected String owner;
}
/**
 * EXAMPLE:
 * select
 *      ID, OWNER, EXPMONTH, EXPYEAR, CARDNUMBER,
 *      ACCOUNT, BANKNAME, SWIFT, CLAZZ_
 * from
 *    ( select
 *          ID, OWNER, EXPMONTH, EXPYEAR, CARDNUMBER,
 *          null as ACCOUNT,
 *          null as BANKNAME,
 *          null as SWIFT,
 *          1 as CLAZZ_
 *      from
 *          CREDITCARD
 *      union all
 *      select
 *          id, OWNER,
 *          null as EXPMONTH,
 *          null as EXPYEAR,
 *          null as CARDNUMBER,
 *          ACCOUNT, BANKNAME, SWIFT,
 *          2 as CLAZZ_
 *      from
 *          BANKACCOUNT
 *    ) as BILLINGDETAILS
 * */
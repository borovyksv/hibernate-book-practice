package com.borovyksv.model.inheritance.joins;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "bd_type") // optional, adds extra column for table discriminators/names
public abstract class JBillingDetails {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    protected String owner;
}
/**
 * EXAMPLE:
 *
 * select
 *      BD.ID, BD.OWNER,
 *      CC.EXPMONTH, CC.EXPYEAR, CC.CARDNUMBER,
 *      BA.ACCOUNT, BA.BANKNAME, BA.SWIFT,
 *      case
 *          when CC.CREDITCARD_ID is not null then 1
 *          when BA.ID is not null then 2
 *          when BD.ID is not null then 0
 *      end
 * from
 *      BILLINGDETAILS BD
 *      left outer join CREDITCARD CC on BD.ID=CC.CREDITCARD_ID
 *      left outer join BANKACCOUNT BA on BD.ID=BA.ID
 * */
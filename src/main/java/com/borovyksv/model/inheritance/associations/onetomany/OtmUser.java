package com.borovyksv.model.inheritance.associations.onetomany;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
public class OtmUser {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<OtmBillingDetails> billingDetails;

    public void setBillingDetails(List<OtmBillingDetails> billingDetails) {
        billingDetails.forEach(bd -> bd.setUser(this));
        this.billingDetails = billingDetails;
    }
}

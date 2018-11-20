package com.borovyksv.model.inheritance.associations.manytoone;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
public class MtoUser {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private MtoBillingDetails defaultBilling;
}

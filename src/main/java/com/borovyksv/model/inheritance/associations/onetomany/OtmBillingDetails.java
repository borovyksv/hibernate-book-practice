package com.borovyksv.model.inheritance.associations.onetomany;

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
public abstract class OtmBillingDetails {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    protected String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private OtmUser user;
}

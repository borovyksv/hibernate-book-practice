package com.borovyksv.model.associations.onetoone.foreigngenerator;

import com.borovyksv.model.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude="address")
@Entity
public class FgUser {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    protected String username;

    @OneToOne(  fetch = FetchType.LAZY, optional = false,
                mappedBy = "user", cascade = CascadeType.PERSIST)
    protected FgAddress address;

    public FgUser(String username) {
        this.username = username;
    }
}

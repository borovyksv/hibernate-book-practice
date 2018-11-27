package com.borovyksv.model.associations.onetomany.jointable;


import com.borovyksv.model.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(exclude = "boughtItems")
@NoArgsConstructor
@Entity
public class OtmJtUser {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "buyer")
    protected Set<OtmJtItem> boughtItems = new HashSet<>();

    public OtmJtUser(String username) {
        this.username = username;
    }
}

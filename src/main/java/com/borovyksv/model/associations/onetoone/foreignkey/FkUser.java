package com.borovyksv.model.associations.onetoone.foreignkey;

import com.borovyksv.model.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FkUser {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR) //<= injects in constructor (app-assigned Id)
    private Long id;
    protected String username;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    protected FkAddress address;

    public FkUser(String username, FkAddress address) {
        this.username = username;
        this.address = address;
    }
}

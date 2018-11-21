package com.borovyksv.model.associations.onetoone.sharedprimarykey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
//    @GeneratedValue(generator = Constants.ID_GENERATOR) //<= injects in constructor (app-assigned Id)
    private Long id;
    protected String username;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    protected Address address;

}

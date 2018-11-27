package com.borovyksv.model.associations.onetoone.jointable;

import com.borovyksv.model.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JtItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR) //<= injects in constructor (app-assigned Id)
    private Long id;
    protected String name;

}

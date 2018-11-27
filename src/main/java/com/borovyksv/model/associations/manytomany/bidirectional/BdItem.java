package com.borovyksv.model.associations.manytomany.bidirectional;

import com.borovyksv.model.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class BdItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ManyToMany(mappedBy = "items")
    protected Set<BdCategory> categories = new HashSet<>();

    public BdItem(String itemName) {
        this.itemName = itemName;
    }
}

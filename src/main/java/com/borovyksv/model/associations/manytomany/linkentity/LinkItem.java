package com.borovyksv.model.associations.manytomany.linkentity;

import com.borovyksv.model.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class LinkItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @OneToMany(mappedBy = "item")
    protected Set<LinkCategorizedItem> categorizedItems = new HashSet<>();

    public LinkItem(String itemName) {
        this.itemName = itemName;
    }
}

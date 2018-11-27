package com.borovyksv.model.associations.manytomany.linkentity;

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

@Getter
@Setter
@EqualsAndHashCode(exclude = "items")
@NoArgsConstructor
@Entity
public class LinkCategory {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    protected Set<LinkCategorizedItem> categorizedItems = new HashSet<>();

    public LinkCategory(String name) {
        this.name = name;
    }
}

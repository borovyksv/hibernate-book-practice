package com.borovyksv.model.associations.manytomany.ternary;

import com.borovyksv.model.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "categorizedItems")
@NoArgsConstructor
@Entity
public class TernaryCategory {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String name;

    @ElementCollection
    @CollectionTable(name = "TERNARY_CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"))
    protected Set<TernaryCategorizedItem> categorizedItems = new HashSet<>();

    public TernaryCategory(String name) {
        this.name = name;
    }
}

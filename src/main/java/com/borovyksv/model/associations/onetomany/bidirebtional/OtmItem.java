package com.borovyksv.model.associations.onetomany.bidirebtional;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OtmItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @OneToMany(cascade = {
            CascadeType.PERSIST, // persist bids that were added to Item
            CascadeType.REMOVE}, // delete all bids on Item deletion
            mappedBy = "item",
            orphanRemoval = true) // if bid has been removed from the collection, then remove it from DB
    protected Set<OtmBid> bids = new HashSet<>();


    public OtmItem(String itemName) {
        this.itemName = itemName;
    }

    public void addBid(OtmBid bid) {
        bids.add(bid);
        bid.setItem(this);
    }

    public void setBids(Set<OtmBid> bids) {
        this.bids = bids;
        bids.forEach(bid -> bid.setItem(this));
    }
}

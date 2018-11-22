package com.borovyksv.model.associations.onetomany.bag;

import com.borovyksv.model.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BagItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @OneToMany(cascade = {
            CascadeType.PERSIST, // persist bids that were added to Item
            CascadeType.REMOVE}, // delete all bids on Item deletion
            mappedBy = "item",
            orphanRemoval = true) // if bid has been removed from the collection, then remove it from DB
    protected Collection<BagBid> bids = new ArrayList<>();


    public BagItem(String itemName) {
        this.itemName = itemName;
    }

    public void addBid(BagBid bid) {
        bids.add(bid);
        bid.setItem(this);
    }

    public void setBids(Collection<BagBid> bids) {
        this.bids = bids;
        bids.forEach(bid -> bid.setItem(this));
    }
}

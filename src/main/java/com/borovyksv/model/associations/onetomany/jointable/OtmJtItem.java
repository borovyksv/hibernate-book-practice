package com.borovyksv.model.associations.onetomany.jointable;

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
public class OtmJtItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @OneToMany(cascade = {
            CascadeType.PERSIST, // persist bids that were added to Item
            CascadeType.REMOVE}, // delete all bids on Item deletion
            mappedBy = "item",
            orphanRemoval = true) // if bid has been removed from the collection, then remove it from DB
    protected Collection<OtmJtBid> bids = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ITEM_BUYER",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "BUYER_ID")
    )
    protected OtmJtUser buyer;


    public OtmJtItem(String itemName) {
        this.itemName = itemName;
    }

    public void addBid(OtmJtBid bid) {
        bids.add(bid);
        bid.setItem(this);
    }

    public void setBids(Collection<OtmJtBid> bids) {
        this.bids = bids;
        bids.forEach(bid -> bid.setItem(this));
    }
}

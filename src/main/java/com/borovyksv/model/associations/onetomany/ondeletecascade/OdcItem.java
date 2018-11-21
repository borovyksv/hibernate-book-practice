package com.borovyksv.model.associations.onetomany.ondeletecascade;

import com.borovyksv.model.Constants;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OdcItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "item")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Set<OdcBid> bids = new HashSet<>();

    public OdcItem(String itemName) {
        this.itemName = itemName;
    }

    public void addBid(OdcBid bid) {
        bids.add(bid);
        bid.setItem(this);
    }

    public void setBids(Set<OdcBid> bids) {
        this.bids = bids;
        bids.forEach(bid -> bid.setItem(this));
    }
}

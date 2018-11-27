package com.borovyksv.model.associations.onetomany.bag;


import com.borovyksv.model.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@EqualsAndHashCode(exclude = "item")
@NoArgsConstructor
@Entity
public class BagBid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String title;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    protected BagItem item;

    public BagBid(String title) {
        this.title = title;
    }

    public BagBid(String title, Double price, BagItem item) {
        this.title = title;
        this.price = price;
        this.item = item;
        item.addBid(this);
    }
}

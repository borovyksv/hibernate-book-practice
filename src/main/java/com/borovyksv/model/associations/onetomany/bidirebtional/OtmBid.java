package com.borovyksv.model.associations.onetomany.bidirebtional;


import com.borovyksv.model.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class OtmBid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String title;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    protected OtmItem item;

    public OtmBid(String title) {
        this.title = title;
    }

    public OtmBid(String title, Double price, OtmItem item) {
        this.title = title;
        this.price = price;
        this.item = item;
        item.addBid(this);
    }
}

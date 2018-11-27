package com.borovyksv.model.associations.onetomany.jointable;


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
public class OtmJtBid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String title;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    protected OtmJtItem item;

    public OtmJtBid(String title) {
        this.title = title;
    }

    public OtmJtBid(String title, Double price, OtmJtItem item) {
        this.title = title;
        this.price = price;
        this.item = item;
        item.addBid(this);
    }
}

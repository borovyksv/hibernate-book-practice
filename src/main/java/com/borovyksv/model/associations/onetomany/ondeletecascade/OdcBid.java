package com.borovyksv.model.associations.onetomany.ondeletecascade;


import com.borovyksv.model.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class OdcBid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String title;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    protected OdcItem item;

    public OdcBid(String title) {
        this.title = title;
    }

    public OdcBid(String title, Double price, OdcItem item) {
        this.title = title;
        this.price = price;
        this.item = item;
        item.addBid(this);
    }
}

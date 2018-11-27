package com.borovyksv.model.associations.onetoone.jointable;

import com.borovyksv.model.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class JtShipment {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR) //<= injects in constructor (app-assigned Id)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ITEM_SHIPMENT",
            joinColumns = @JoinColumn(name = "SHIPMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID",
                    nullable = false,
                    unique = true)
    )
    protected JtItem item;

    public JtShipment(JtItem item) {
        this.item = item;
    }
}

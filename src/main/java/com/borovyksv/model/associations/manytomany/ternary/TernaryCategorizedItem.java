package com.borovyksv.model.associations.manytomany.ternary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("ALL")
@Embeddable
public class TernaryCategorizedItem {

    @CreationTimestamp
    @NotNull
    protected LocalDateTime addedOn;

    @ManyToOne
    @JoinColumn(name = "USER_ID", updatable = false)
    @NotNull
    protected TernaryUser addedBy;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", updatable = false)
    protected TernaryItem item;

    public TernaryCategorizedItem(@NotNull TernaryUser addedBy, TernaryItem item) {
        this.addedBy = addedBy;
        this.item = item;
    }
}
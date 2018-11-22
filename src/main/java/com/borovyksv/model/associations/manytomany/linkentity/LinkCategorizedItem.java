package com.borovyksv.model.associations.manytomany.linkentity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LINK_CATEGORY_ITEM")
@Immutable
public class LinkCategorizedItem {


    @EmbeddedId
    protected Id id = new Id();

    @Column(updatable = false)
    protected String addedBy;

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime addedOn;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    protected LinkCategory category;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", insertable = false, updatable = false)
    protected LinkItem item;

    public LinkCategorizedItem(String addedBy, LinkCategory category, LinkItem item) {
        this.addedBy = addedBy;
        this.category = category;
        this.item = item;

        this.id.categoryId = category.getId();
        this.id.itemId = item.getId();
        category.getCategorizedItems().add(this);
        item.getCategorizedItems().add(this);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "CATEGORY_ID")
        protected Long categoryId;

        @Column(name = "ITEM_ID")
        protected Long itemId;
    }
}
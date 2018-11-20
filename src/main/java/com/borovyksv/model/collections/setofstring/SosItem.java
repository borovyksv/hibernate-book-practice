package com.borovyksv.model.collections.setofstring;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class SosItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ElementCollection
    @CollectionTable(name = "Image",                          //overrides table "SosItem_images" -> "Image",
            joinColumns = @JoinColumn(name = "item_id"))      //overrides column "SosItem_id" -> "item_id",
    @Column(name = "filename")                                //overrides column "images" -> "filename",
    protected Set<String> images = new HashSet<>();
}
/**
 * EXAMPLE:
 *
 * create table SosItem (
 *        id bigint not null,
 *        item_name varchar(255),
 *        primary key (id)
 *     )
 *
 * create table Image (
 *        item_id bigint not null,
 *        filename varchar(255)
 *     )
 *
 * alter table Image
 *        add constraint Image_SosItem_foreign_key
 *        foreign key (item_id)
 *        references SosItem
 * */
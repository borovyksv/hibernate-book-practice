package com.borovyksv.model.collections.listofstrings;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class LosItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ElementCollection
    @CollectionTable(name = "Image",                          //overrides table "SosItem_images" -> "Image",
            joinColumns = @JoinColumn(name = "item_id"))      //overrides column "SosItem_id" -> "item_id",
    @Column(name = "filename")                                //overrides column "images" -> "filename",
    @OrderColumn
    protected List<String> images = new ArrayList<>();
}

/**
 * EXAMPLE:
 *
 * create table Image (
 *        item_id bigint not null,
 *         filename varchar(255),
 *         images_ORDER integer not null,
 *         primary key (item_id, images_ORDER)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     create table LosItem (
 *        id bigint not null,
 *         item_name varchar(255),
 *         primary key (id)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     alter table Image
 *        add constraint FKrdxyfs75ad7cpecvieh4a6jtq
 *        foreign key (item_id)
 *        references LosItem (id)
 * */
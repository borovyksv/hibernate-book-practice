package com.borovyksv.model.collections.orderedmapofstrings;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class OmosItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ElementCollection
    @CollectionTable(name = "OmosItem_image",
            joinColumns = @JoinColumn(name = "item_id"))      //overrides column "OosItem_id" -> "item_id",
    @MapKeyColumn(name = "filename")                          //key
    @Column(name = "imagename")                               //value
//    @javax.persistence.OrderBy                              //Only one possible order: "FILENAME asc"
    @org.hibernate.annotations.OrderBy(clause = "filename desc")
    protected Map<String, String> images = new LinkedHashMap<>();
}

/**
 * Keep in mind that the elements of ordered collections are only in the desired order
 * when they’re loaded. As soon as you add and remove elements, the iteration order of
 * the collections might be different than “by filename”; they behave like regular linked
 * sets, maps, or lists.
 *
 * EXAMPLE:
 *
 * create table OmosItem (
 *        id bigint not null,
 *         item_name varchar(255),
 *         primary key (id)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     create table OmosItem_image (
 *        item_id bigint not null,
 *         imagename varchar(255),
 *         filename varchar(255) not null,
 *         primary key (item_id, filename)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     alter table OmosItem_image
 *        add constraint FKrbjqkjfjhtuu0sy7v22igu9xx
 *        foreign key (item_id)
 *        references OmosItem (id)
 * */
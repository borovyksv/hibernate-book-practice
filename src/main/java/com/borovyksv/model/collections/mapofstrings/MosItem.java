package com.borovyksv.model.collections.mapofstrings;

import com.borovyksv.model.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class MosItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ElementCollection
    @CollectionTable(name = "MosItem_image",
            joinColumns = @JoinColumn(name = "item_id"))      //overrides column "SosItem_id" -> "item_id",
    @MapKeyColumn(name = "filename")                          //key
    @Column(name = "imagename")                               //value
    protected Map<String, String> images = new HashMap<>();
}

/**
 * EXAMPLE:
 *
 * create table Image (
 *        item_id bigint not null,
 *         imagename varchar(255),
 *         filename varchar(255) not null,
 *         primary key (item_id, filename)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     create table MosItem (
 *        id bigint not null,
 *         item_name varchar(255),
 *         primary key (id)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     alter table Image
 *        add constraint FKdi59i5qggttd5gm1k2d4ip723
 *        foreign key (item_id)
 *        references MosItem (id)
 * */
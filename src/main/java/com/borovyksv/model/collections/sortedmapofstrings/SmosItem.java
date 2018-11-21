package com.borovyksv.model.collections.sortedmapofstrings;

import com.borovyksv.config.ReverseStringComparator;
import com.borovyksv.model.Constants;
import lombok.*;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.SortedMap;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class SmosItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ElementCollection
    @CollectionTable(name = "SmosItem_image",
            joinColumns = @JoinColumn(name = "item_id"))      //overrides column "SosItem_id" -> "item_id",
    @MapKeyColumn(name = "filename")                          //key
    @Column(name = "imagename")                               //value
    @SortComparator(ReverseStringComparator.class)
    protected SortedMap<String, String> images = new TreeMap<>();
}

/**
 * EXAMPLE:
 *
 * create table SmosItem (
 *        id bigint not null,
 *         item_name varchar(255),
 *         primary key (id)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     create table SmosItem_image (
 *        item_id bigint not null,
 *         imagename varchar(255),
 *         filename varchar(255) not null,
 *         primary key (item_id, filename)
 *     ) engine=InnoDB
 * Hibernate:
 *
 *     alter table SmosItem_image
 *        add constraint FK741imnbngkthk3u9nd57t4l6r
 *        foreign key (item_id)
 *        references SmosItem (id)
 * */
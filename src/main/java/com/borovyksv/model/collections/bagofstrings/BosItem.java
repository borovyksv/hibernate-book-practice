package com.borovyksv.model.collections.bagofstrings;

import com.borovyksv.model.Constants;
import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class BosItem {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String itemName;

    @ElementCollection
    @CollectionTable
    @Column(name = "filename")                              //overrides column "images" -> "filename",
    @CollectionId(
            columns = @Column(name = "image_id"),
            type = @Type(type = "long"),
            generator = Constants.ID_GENERATOR)
    protected Collection<String> images = new HashSet<>();

}
/**
 * EXAMPLE:
 *
 * create table BosItem (
 *        id bigint not null,
 *        item_name varchar(255),
 *        primary key (id)
 *     )
 *
 *create table BosItem_images (
 *        BosItem_id bigint not null,
 *        filename varchar(255),
 *        image_id bigint not null,
 *        primary key (image_id)
 *     )
 *
 *     alter table BosItem_images
 *        add constraint FKnrk1eaf4cbftpwa496sw73w2w
 *        foreign key (BosItem_id)
 *        references BosItem
 * */
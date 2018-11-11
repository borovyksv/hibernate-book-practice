package com.borovyksv.model.helloworld;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity(name = "message")
public class Message {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;
    private String text;
    private Payload payload;

    @AttributeOverrides({
            @AttributeOverride(name = "title", column = @Column(name = "hidden_title")),
            @AttributeOverride(name = "data", column = @Column(name = "hidden_data"))
    })
    private Payload hiddenPayload;

    @UpdateTimestamp
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created;
}

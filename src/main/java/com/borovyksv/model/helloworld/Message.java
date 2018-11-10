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
//    private Payload hiddenPayload;

    @UpdateTimestamp
    private LocalDateTime lastModified;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created;
}

package com.borovyksv.model.helloworld;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
}

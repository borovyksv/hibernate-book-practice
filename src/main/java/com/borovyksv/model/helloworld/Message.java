package com.borovyksv.model.helloworld;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
public class Message {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;
    private String text;
}

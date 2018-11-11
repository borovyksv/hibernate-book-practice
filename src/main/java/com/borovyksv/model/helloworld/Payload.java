package com.borovyksv.model.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Payload {

    @Column(nullable = false)
    @NotNull
    String title;

    @Column(nullable = false)
    @NotNull
    String data;
}

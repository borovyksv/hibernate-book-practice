package com.borovyksv.model.auction;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@ToString
@EqualsAndHashCode

@Embeddable
public class Address {
    static final String STREET = "street";
    static final String ZIPCODE = "zipcode";
    static final String CITY = "city";

    private String street;
    private String zipcode;
    private String city;
}

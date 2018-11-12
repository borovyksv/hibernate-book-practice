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
    public static final String STREET = "street";
    public static final String ZIPCODE = "zipcode";
    public static final String CITY = "city";

    private String street;
    private String zipcode;
    private String city;
}

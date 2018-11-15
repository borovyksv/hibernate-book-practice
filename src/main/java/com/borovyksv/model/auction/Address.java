package com.borovyksv.model.auction;

import com.borovyksv.config.ZipCodeConverter;
import com.borovyksv.model.auction.zipcode.Zipcode;
import lombok.*;

import javax.persistence.Convert;
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

    @Convert(converter = ZipCodeConverter.class)
    private Zipcode zipcode;
    private String street;
    private String city;


}

package com.borovyksv.config;

import com.borovyksv.model.auction.zipcode.GermanZipcode;
import com.borovyksv.model.auction.zipcode.SwissZipcode;
import com.borovyksv.model.auction.zipcode.Zipcode;

import javax.persistence.AttributeConverter;

public class ZipCodeConverter implements AttributeConverter<Zipcode, String> {

    @Override
    public String convertToDatabaseColumn(Zipcode zipcode) {
        if (zipcode == null) return null;
        return zipcode.getValue();
    }

    @Override
    public Zipcode convertToEntityAttribute(String s) {
        if (s == null) return null;
        switch (s.length()) {
            case GermanZipcode.ZIPCODE_LENGTH: return new GermanZipcode();
            case SwissZipcode.ZIPCODE_LENGTH: return new SwissZipcode();
            default: throw new IllegalArgumentException("Unsupported zipcode in DB");
        }
    }
}

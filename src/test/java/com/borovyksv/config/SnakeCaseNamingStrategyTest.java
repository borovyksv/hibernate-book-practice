package com.borovyksv.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnakeCaseNamingStrategyTest {
    private SnakeCaseNamingStrategy strategy = SnakeCaseNamingStrategy.INSTANCE;

    @Test
    public void testConvertCamelCaseToSnakeCase() {
        assertEquals("",                                when(""));
        assertEquals("first",                           when("first"));
        assertEquals("first_name",                      when("firstName"));
        assertEquals("some_complex_name",               when("someComplexName"));
        assertEquals("360_bed",                         when("360Bed"));
        assertEquals("360",                             when("360"));
        assertEquals("y_360_bed",                       when("y360Bed"));
        assertEquals("new_360_bed",                     when("new360Bed"));
        assertEquals("FILENAME",                        when("FILENAME"));
        assertEquals("ITEM_ID",                         when("ITEM_ID"));
    }

    private String when(String firstName) {
        return strategy.getStringSnakeCase(firstName);
    }

}
package com.borovyksv.model.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeCaseNamingStrategyTest {
    private SnakeCaseNamingStrategy strategy = SnakeCaseNamingStrategy.INSTANCE;

    @Test
    public void testConvertCamelCaseToSnakeCase() {
        assertEquals("first",                           when("first"));
        assertEquals("first_name",                      when("firstName"));
        assertEquals("some_complex_name",               when("someComplexName"));
        assertEquals("camel_case_to_something_else",    when("CamelCaseToSomethingElse"));
        assertEquals("360_bed",                         when("360Bed"));
        assertEquals("360",                             when("360"));
        assertEquals("y_360_bed",                       when("y360Bed"));
        assertEquals("new_360_bed",                     when("new360Bed"));
    }

    private String when(String firstName) {
        return strategy.getStringSnakeCase(firstName);
    }

}
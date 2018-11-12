package com.borovyksv.util;

import com.borovyksv.model.auction.Address;
import com.borovyksv.model.auction.User;
import com.borovyksv.model.helloworld.Message;
import com.borovyksv.model.helloworld.Payload;

import javax.persistence.EntityManager;

public class TestUtil {

    public static Message persistHelloWorldMessage(EntityManager em) {
        Message message = new Message();
        message.setText("Hello world!");
        message.setPayload(new Payload("Hello-title", "Hello-data"));
        em.persist(message);
        return message;
    }

    public static User getUser() {
        Address address = Address.builder()
                .street("Main")
                .zipcode("12312")
                .city("Vice")
                .build();
        return User.builder()
                .firstName("Mr")
                .lastName("Smith")
                .userName("Mr. Smith")
                .homeAddress(address)
                .billingAddress(address)
                .shippingAddress(address)
                .build();
    }
}
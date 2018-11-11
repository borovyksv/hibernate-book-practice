package com.borovyksv.util;

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
}
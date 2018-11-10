package com.borovyksv;

import com.borovyksv.model.helloworld.Message;
import com.borovyksv.model.helloworld.Payload;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;

public class TestHelper {

    static Message persistHelloWorldMessage(EntityManager em) {
        Message message = new Message();
        message.setText("Hello world!");
        message.setPayload(new Payload(true,"Hello-title", "Hello-data"));
        em.persist(message);
        return message;
    }
}
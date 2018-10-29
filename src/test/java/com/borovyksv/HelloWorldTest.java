package com.borovyksv;

import com.borovyksv.model.helloworld.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class HelloWorldTest {

    static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("HelloWorld");


    @Test
    public void testASave(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        persistHelloWorldMessage(em);

        tx.commit();
        em.close();
    }

    @Test
    public void testBGetAndDirtyUpdate(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        persistHelloWorldMessage(em);
        List<Message> messages = em.createQuery("select m from Message m").getResultList();
        assertEquals(1, messages.size());
        Message persistedMessage = messages.get(0);
        String text = persistedMessage.getText();
        assertEquals(text, "Hello world!");
        System.out.println(text);
        persistedMessage.setText("Take me to you leader!");

        tx.commit();
        em.close();
    }


    private void persistHelloWorldMessage(EntityManager em) {
        Message message = new Message();
        message.setText("Hello world!");
        em.persist(message);
    }
}
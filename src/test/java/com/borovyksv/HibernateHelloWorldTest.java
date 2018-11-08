package com.borovyksv;

import com.borovyksv.model.helloworld.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class HibernateHelloWorldTest {

    static SessionFactory sessionFactory = new Configuration().configure()
            .buildSessionFactory();


    @Test
    public void testASave(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        persistHelloWorldMessage(session);

        transaction.commit();
        session.close();
    }

    @Test
    public void testBGetAndDirtyUpdate(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Message> messages = session.createCriteria(Message.class).list();
        assertEquals(1, messages.size());
        Message persistedMessage = messages.get(0);
        String text = persistedMessage.getText();
        assertEquals(text, "Hello world!");
        System.out.println(text);
        persistedMessage.setText("Take me to you leader!");

        transaction.commit();
        session.close();
    }

    private void persistHelloWorldMessage(EntityManager em) {
        Message message = new Message();
        message.setText("Hello world!");
        em.persist(message);
    }

}
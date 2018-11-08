package com.borovyksv;

import com.borovyksv.base.BaseHibernateTest;
import com.borovyksv.model.helloworld.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class HibernateHelloWorldTest extends BaseHibernateTest {

    @BeforeClass
    public static void init(){
        sessionFactory = getSessionFactory(HibernateConfig.MySQL);
    }

    @Test
    public void testASave(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        persistHelloWorldMessage(session);
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
        Message persistedMessage = messages.get(0);
        String text = persistedMessage.getText();
        assertEquals("Hello world!", text);
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
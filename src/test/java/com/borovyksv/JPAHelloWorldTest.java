package com.borovyksv;

import com.borovyksv.base.BaseJpaTest;
import com.borovyksv.model.helloworld.Message;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class JPAHelloWorldTest extends BaseJpaTest {

    @BeforeClass
    public static void init(){
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testASave() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        persistHelloWorldMessage(em);
        persistHelloWorldMessage(em);
        tx.commit();
        em.close();
    }

    @Test
    public void testBGetAndDirtyUpdate() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        persistHelloWorldMessage(em);
        List<Message> messages = em.createQuery("select m from Message m").getResultList();
        Message persistedMessage = messages.get(0);
        String text = persistedMessage.getText();
        assertEquals("Hello world!", text);
        System.out.println(text);
        persistedMessage.setText("Take me to you leader!");

        tx.commit();
        em.close();
    }


    private void persistHelloWorldMessage(EntityManager em) {
        Message message = new Message();
        message.setText("Hello world!");
        assertNull(message.getId());
        em.persist(message);
        assertNotNull(message.getId());

    }
}
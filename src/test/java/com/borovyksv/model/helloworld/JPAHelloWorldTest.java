package com.borovyksv.model.helloworld;

import com.borovyksv.base.BaseJpaTest;
import com.borovyksv.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class JPAHelloWorldTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testASave() {
        executeInTransaction(entityManager -> {
            TestUtil.persistHelloWorldMessage(entityManager);
            TestUtil.persistHelloWorldMessage(entityManager);
        });
    }

    @Test
    public void testBGetAndDirtyUpdate() {
        executeInTransaction(entityManager -> {
            TestUtil.persistHelloWorldMessage(entityManager);
            List<Message> messages = entityManager.createQuery("select m from message m").getResultList();
            Message persistedMessage = messages.get(0);
            String text = persistedMessage.getText();
            assertEquals("Hello world!", text);
            System.out.println(text);
            persistedMessage.setText("Take me to you leader!");
        });
    }

}
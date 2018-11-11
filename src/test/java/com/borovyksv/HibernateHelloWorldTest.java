package com.borovyksv;

import com.borovyksv.base.BaseHibernateTest;
import com.borovyksv.model.helloworld.Message;
import com.borovyksv.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(JUnit4.class)
public class HibernateHelloWorldTest extends BaseHibernateTest {

    @BeforeClass
    public static void init() {
        sessionFactory = getSessionFactory(HibernateConfig.H2);
    }

    @Test
    public void testSave() {
        executeInTransaction(session -> {
            TestUtil.persistHelloWorldMessage(session);
            TestUtil.persistHelloWorldMessage(session);
        });
    }

    @Test
    public void testGetAndDirtyUpdate() {
        executeInTransaction(session -> {
            Message persistedMessage = TestUtil.persistHelloWorldMessage(session);
            String text = persistedMessage.getText();
            System.out.println(persistedMessage.getPayload().getTitle());
//
            assertEquals("Hello world!", text);
            persistedMessage.setText("Take me to you leader!");
        });
    }

    @Test
    public void testModifyCreateTimestamp() {
        LocalDateTime newDate = LocalDateTime.of(2020, Month.DECEMBER, 1, 12, 0);

        Message savedMessage = executeInTransaction(session -> {
            Message persistedMessage = TestUtil.persistHelloWorldMessage(session);
            persistedMessage.setCreated(newDate);
            return persistedMessage;
        });

        Message message = executeInTransaction(session -> {
            return getMessage(session, savedMessage.getId());
        });

        assertNotEquals(newDate, message.getCreated());
    }

}
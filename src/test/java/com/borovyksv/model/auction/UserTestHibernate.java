package com.borovyksv.model.auction;

import com.borovyksv.base.BaseHibernateTest;
import com.borovyksv.model.auction.zipcode.GermanZipcode;
import com.borovyksv.model.auction.zipcode.SwissZipcode;
import com.borovyksv.util.TestUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("all")
public class UserTestHibernate extends BaseHibernateTest {

    @BeforeClass
    public static void init() {
        sessionFactory = getSessionFactory(HibernateConfig.H2);
    }

    @Test
    public void testCreate() {
        User user = TestUtil.getUser();
        executeInTransaction(session -> {
            session.persist(user);
        });
    }

    @Test
    public void testRead() {
        executeInTransaction(session -> {
            User user = TestUtil.getUser();
            session.persist(user);
            List<User> users = getAllUsers(session);
            System.out.println(users);
            assertFalse(users.isEmpty());
        });
    }

    @Test
    public void testUpdate() {
        executeInTransaction(session -> {
            User originalUser = TestUtil.getUser();
            session.persist(originalUser);
            User retrievedUser = getUserById(session, originalUser.getId());

            String newUsername = "New Username";
            retrievedUser.setFirstName(newUsername);
            assertEquals(newUsername, originalUser.getFirstName());
        });
    }

    @Test
    public void testDelete() {
        executeInTransaction(entityManager -> {
            User user = TestUtil.getUser();
            entityManager.persist(user);

            List<User> allUsers1 = getAllUsers(entityManager);
            assertTrue(allUsers1.contains(user));

            entityManager.remove(user);
            List<User> allUsers2 = getAllUsers(entityManager);
            assertFalse(allUsers2.contains(user));
        });
    }

    @Test //NOTE: Attrubute Converter cause redundant dirty updates
    public void testZipcodeConverter() {
        executeInTransaction(entityManager -> {
            User originalUser = TestUtil.getUser();
            entityManager.persist(originalUser);
            User retrievedUser = getUserById(entityManager, originalUser.getId());
            assertTrue(retrievedUser.getHomeAddress().getZipcode() instanceof GermanZipcode);

            retrievedUser.getHomeAddress().setZipcode(new SwissZipcode("1234"));
            User retrievedUser2 = getUserById(entityManager, originalUser.getId());
            assertTrue(retrievedUser2.getHomeAddress().getZipcode() instanceof SwissZipcode);
        });
    }

    private List<User> getAllUsers(Session session) {
        return session.createCriteria(User.class).list();
    }

    private User getUserById(Session session, Long id) {
        return (User) session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
}

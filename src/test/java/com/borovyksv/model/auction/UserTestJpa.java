package com.borovyksv.model.auction;

import com.borovyksv.base.BaseJpaTest;
import com.borovyksv.model.auction.zipcode.GermanZipcode;
import com.borovyksv.model.auction.zipcode.SwissZipcode;
import com.borovyksv.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("all")
public class UserTestJpa extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testCreate() {
        User user = TestUtil.getUser();
        executeInTransaction(entityManager -> {
            entityManager.persist(user);
        });
    }

    @Test
    public void testRead() {
        executeInTransaction(entityManager -> {
            User user = TestUtil.getUser();
            entityManager.persist(user);
            List<User> users = getAllUsers(entityManager);
            System.out.println(users);
            assertFalse(users.isEmpty());
        });
    }

    @Test
    public void testUpdate() {
        executeInTransaction(entityManager -> {
            User originalUser = TestUtil.getUser();
            entityManager.persist(originalUser);
            User retrievedUser = getUserById(entityManager, originalUser.getId());

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

    @Test //NOTE: Attribute Converter cause redundant dirty updates
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

    private List<User> getAllUsers(EntityManager entityManager) {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    private User getUserById(EntityManager entityManager, Long id) {
        return entityManager.createQuery("from User where id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

package com.borovyksv.model.auction;

import com.borovyksv.base.BaseJpaTest;
import com.borovyksv.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

public class ItemTestJpa extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testCreate() {
        Item item = TestUtil.getItem();
        executeInTransaction(entityManager -> {
            entityManager.persist(item);
        });
    }

    @Test
    public void testRead() {
        executeInTransaction(entityManager -> {
            Item item = TestUtil.getItem();
            entityManager.persist(item);
            List<Item> items = getAllItems(entityManager);
            System.out.println(items);
            assertFalse(items.isEmpty());
        });
    }

    @Test
    public void testUpdate() {
        executeInTransaction(entityManager -> {
            Item originalItem = TestUtil.getItem();
            entityManager.persist(originalItem);
            Item retrievedItem = getItemById(entityManager, originalItem.getId());

            String newItemName = "New ItemName";
            retrievedItem.setName(newItemName);
            assertEquals(newItemName, originalItem.getName());
        });
    }

    @Test
    public void testDelete() {
        executeInTransaction(entityManager -> {
            Item item = TestUtil.getItem();
            entityManager.persist(item);

            List<Item> allItems1 = getAllItems(entityManager);
            assertTrue(allItems1.contains(item));

            entityManager.remove(item);
            List<Item> allItems2 = getAllItems(entityManager);
            assertFalse(allItems2.contains(item));
        });
    }


    private List<Item> getAllItems(EntityManager entityManager) {
        return entityManager.createQuery("from Item", Item.class).getResultList();
    }

    private Item getItemById(EntityManager entityManager, Long id) {
        return entityManager.createQuery("from Item where id = :id", Item.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

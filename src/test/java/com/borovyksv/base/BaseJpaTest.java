package com.borovyksv.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
@SuppressWarnings("unchecked")
public abstract class BaseJpaTest<E> {
    protected static EntityManagerFactory emf;

    /**
     * persistence-unit name in persistence.xml
     */
    protected enum JpaConfig {
        H2, MySQL
    }

    protected static EntityManagerFactory getEntityManagerFactory() {
        return getEntityManagerFactory(JpaConfig.H2);
    }

    protected static EntityManagerFactory getEntityManagerFactory(JpaConfig jpaConfig) {
        return Persistence.createEntityManagerFactory(jpaConfig.toString());
    }

    protected abstract E getTestEntity();
    protected abstract String getEntityTableName();
    protected abstract Long getEntityId(E entity);

    // next 2 methods should update and get the same value of the test entities
    protected abstract String getOriginalEntityValue(E originalEntity);
    protected abstract String updateAndGetEntityValue(E entityToUpdate);

    @Test
    public void testCreate() {
        E entity = getTestEntity();
        executeInTransaction(entityManager -> {
            entityManager.persist(entity);
        });
    }

    @Test
    public void testRead() {
        executeInTransaction(entityManager -> {
            E entity = getTestEntity();
            entityManager.persist(entity);
            List<E> entities = getAllEntities(entityManager);
            System.out.println(entities);
            assertFalse(entities.isEmpty());
        });
    }

    @Test
    public void testUpdate() {
        executeInTransaction(entityManager -> {
            E originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            E retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));

            String updatedValue = updateAndGetEntityValue(retrievedEntity);
            String originalValue = getOriginalEntityValue(originalEntity);

            assertEquals(updatedValue, originalValue);
        });
    }

    @Test
    public void testDelete() {
        executeInTransaction(entityManager -> {
            E entity = getTestEntity();
            entityManager.persist(entity);

            List<E> allEntities1 = getAllEntities(entityManager);
            assertTrue(allEntities1.contains(entity));

            entityManager.remove(entity);
            List<E> allEntities2 = getAllEntities(entityManager);
            assertFalse(allEntities2.contains(entity));
        });
    }


    protected List<E> getAllEntities(EntityManager entityManager) {
        return entityManager.createQuery("from " + getEntityTableName()).getResultList();
    }


    protected E getEntityById(EntityManager entityManager, Long id) {
        return (E) entityManager.createQuery("from " + getEntityTableName() + " where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }


    protected <T> T executeInTransaction(Function<EntityManager, T> function) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        T apply = function.apply(em);

        tx.commit();
        em.close();
        return apply;
    }

    protected void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        consumer.accept(em);

        tx.commit();
        em.close();
    }
}

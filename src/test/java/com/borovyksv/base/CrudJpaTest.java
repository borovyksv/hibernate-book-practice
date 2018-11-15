package com.borovyksv.base;

import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public abstract class CrudJpaTest<E> extends BaseJpaTest {

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


}

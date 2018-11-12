package com.borovyksv.base;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

@RunWith(JUnit4.class)
public abstract class BaseJpaTest {
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

package com.borovyksv.base;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}

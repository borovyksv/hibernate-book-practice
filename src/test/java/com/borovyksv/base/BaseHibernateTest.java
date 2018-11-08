package com.borovyksv.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BaseHibernateTest {
    protected static SessionFactory sessionFactory;

    protected enum HibernateConfig {
        H2("h2.cfg.xml"), MySQL("mysql.cfg.xml");

        private String configFileName;
        HibernateConfig(String configFileName) { this.configFileName = configFileName; }
    }

    protected static SessionFactory getSessionFactory() {
        return getSessionFactory(HibernateConfig.H2);
    }
    protected static SessionFactory getSessionFactory(HibernateConfig HibernateConfig) {
        return new Configuration().configure(HibernateConfig.configFileName).buildSessionFactory();
    }
}

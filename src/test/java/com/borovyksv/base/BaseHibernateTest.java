package com.borovyksv.base;

import com.borovyksv.model.helloworld.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("all")
public abstract class BaseHibernateTest {
    protected static SessionFactory sessionFactory;

    protected List<Message> getAllMessages(Session session) {
        return session.createCriteria(Message.class).list();
    }

    protected Message getMessage(Session session, Long id) {
        return (Message) session.createCriteria(Message.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    protected enum HibernateConfig {
        H2("h2.cfg.xml"), MySQL("mysql.cfg.xml");
        private String configFileName;

        HibernateConfig(String configFileName) {
            this.configFileName = configFileName;
        }
    }

    protected static SessionFactory getSessionFactory() {
        return getSessionFactory(HibernateConfig.H2);
    }

    protected static SessionFactory getSessionFactory(HibernateConfig HibernateConfig) {
        return new Configuration().configure(HibernateConfig.configFileName).buildSessionFactory();
    }

    protected <T> T executeInTransaction(Function<Session, T> function) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        T apply = function.apply(session);

        transaction.commit();
        session.close();
        return apply;
    }

    protected void executeInTransaction(Consumer<Session> consumer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        consumer.accept(session);

        transaction.commit();
        session.close();
    }
}

package com.borovyksv.model.inheritance.associations.manytoone;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Entity;

import static org.junit.Assert.assertEquals;

@Entity
public class MtoUserTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPersistFindJPQL() {

        executeInTransaction(entityManager -> {
            MtoBankAccount bankAccount = getBankAccount();
            MtoUser user = getUser();
            user.setDefaultBilling(bankAccount);
            entityManager.persist(bankAccount);
            entityManager.persist(user);

            MtoUser userFromDb = entityManager
                    .createQuery("select u from MtoUser u JOIN FETCH u.defaultBilling b WHERE u.id = :id", MtoUser.class)
                    .setParameter("id", user.getId())
                    .getSingleResult();
            assertEquals(user.getName(), userFromDb.getName());
            assertEquals(bankAccount.getOwner(), userFromDb.getDefaultBilling().getOwner());
        });
    }

    @Test
    public void testPersistFindHibernate() {

        MtoBankAccount bankAccount = getBankAccount();
        MtoUser user = getUser();
        user.setDefaultBilling(bankAccount);
        executeInTransaction(entityManager -> {
            entityManager.persist(bankAccount);
            entityManager.persist(user);
        });
        executeInTransaction(entityManager -> {
            MtoUser userFromDb = entityManager.find(MtoUser.class, user.getId());
            assertEquals(user.getName(), userFromDb.getName());
            assertEquals(bankAccount.getOwner(), userFromDb.getDefaultBilling().getOwner());
        });

    }

    private MtoUser getUser() {
        return MtoUser.builder().name("SomeUser").build();
    }

    protected MtoBankAccount getBankAccount() {
        return MtoBankAccount.builder()
                .owner("owner")
                .account("Some bank account")
                .bankName("World Wide Bank")
                .swift("AD1200012030200359100100")
                .build();
    }
}
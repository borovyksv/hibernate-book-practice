package com.borovyksv.model.inheritance.associations.onetomany;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Entity
public class OtmUserTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPersistFindJPQL() {
        executeInTransaction(entityManager -> {
            OtmUser user = getUser();
            List<OtmBillingDetails> billingDetails = getBillingDetails();
            user.setBillingDetails(billingDetails);
            billingDetails.forEach(entityManager::persist);
            entityManager.persist(user);

            OtmUser userFromDb = entityManager
                    .createQuery("select u from OtmUser u JOIN FETCH u.billingDetails b WHERE u.id = :id", OtmUser.class)
                    .setParameter("id", user.getId())
                    .getSingleResult();
            assertEquals(user.getName(), userFromDb.getName());
            assertEquals(billingDetails.size(), userFromDb.getBillingDetails().size());
        });
    }

    private List<OtmBillingDetails> getBillingDetails() {
        OtmBankAccount bankAccount = getBankAccount();
        OtmBankAccount bankAccount1 = getBankAccount();

        List<OtmBillingDetails> billingDetails = new ArrayList<>();
        billingDetails.add(bankAccount);
        billingDetails.add(bankAccount1);
        return billingDetails;
    }

    @Test
    public void testPersistFindHibernate() {

        OtmUser user = getUser();
        List<OtmBillingDetails> billingDetails = getBillingDetails();
        user.setBillingDetails(billingDetails);

        executeInTransaction(entityManager -> {
            billingDetails.forEach(entityManager::persist);
            entityManager.persist(user);
        });
        executeInTransaction(entityManager -> {
            OtmUser userFromDb = entityManager.find(OtmUser.class, user.getId());
            assertEquals(user.getName(), userFromDb.getName());
            assertEquals(billingDetails.size(), userFromDb.getBillingDetails().size());
        });

    }

    private OtmUser getUser() {
        return OtmUser.builder().name("SomeUser").build();
    }

    protected OtmBankAccount getBankAccount() {
        return OtmBankAccount.builder()
                .owner("owner")
                .account("Some bank account")
                .bankName("World Wide Bank")
                .swift("AD1200012030200359100100")
                .build();
    }
}
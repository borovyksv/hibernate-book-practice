package com.borovyksv.model.inheritance.mappedsuperclass;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class BillingDetailsTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPolymorphicQuery() {
        CreditCard creditCard = CreditCard.builder().owner("owner").cardNumber("7894 9874 7894 1234").expMonth("March").expYear("2020").build();
        BankAccount bankAccount = BankAccount.builder().owner("owner").account("Some bank account").bankName("World Wide Bank").swift("AD1200012030200359100100").build();
        executeInTransaction(entityManager -> {entityManager.persist(creditCard);});
        executeInTransaction(entityManager -> {entityManager.persist(bankAccount);});
        executeInTransaction(entityManager -> {
            List<BillingDetails> billingDetails = entityManager
                    .createQuery("from com.borovyksv.model.inheritance.mappedsuperclass.BillingDetails", BillingDetails.class)
                    .getResultList();
            System.out.println(billingDetails);
            assertFalse(billingDetails.isEmpty());
            assertTrue(billingDetails.contains(creditCard));
            assertTrue(billingDetails.contains(bankAccount));
        });
    }
}

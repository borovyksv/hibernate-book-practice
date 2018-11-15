package com.borovyksv.model.inheritance.tableperclass;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TBillingsDetailsTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPolymorphicQuery() {
        TCreditCard creditCard = TCreditCard.builder().owner("owner").cardNumber("7894 9874 7894 1234").expMonth("March").expYear("2020").build();
        TBankAccount bankAccount = TBankAccount.builder().owner("owner").account("Some bank account").bankName("World Wide Bank").swift("AD1200012030200359100100").build();
        executeInTransaction(entityManager -> {entityManager.persist(creditCard);});
        executeInTransaction(entityManager -> {entityManager.persist(bankAccount);});
        executeInTransaction(entityManager -> {
            List<TBillingDetails> billingDetails = entityManager
                    .createQuery("from TBillingDetails", TBillingDetails.class)
                    .getResultList();
            System.out.println(billingDetails);
            assertFalse(billingDetails.isEmpty());
            assertTrue(billingDetails.contains(creditCard));
            assertTrue(billingDetails.contains(bankAccount));
        });
    }
}

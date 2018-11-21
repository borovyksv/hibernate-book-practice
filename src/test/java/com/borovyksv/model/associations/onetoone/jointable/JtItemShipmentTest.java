package com.borovyksv.model.associations.onetoone.jointable;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class JtItemShipmentTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testJoinTableCreation() {
        executeInTransaction(em -> {
            JtItem item = new JtItem();
            em.persist(item);

            JtShipment auctionShipment = new JtShipment(item);
            em.persist(auctionShipment);
        });

    }
}
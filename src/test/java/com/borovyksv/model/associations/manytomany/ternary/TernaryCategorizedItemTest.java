package com.borovyksv.model.associations.manytomany.ternary;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class TernaryCategorizedItemTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPersistAssociation() {
        executeInTransaction(em -> {
            TernaryCategory someCategory = new TernaryCategory("Some Category");
            TernaryCategory anotherCategory = new TernaryCategory("Another Category");
            em.persist(someCategory);
            em.persist(anotherCategory);

            TernaryItem someItem = new TernaryItem("Some Item");
            TernaryItem anotherItem = new TernaryItem("Another Item");
            em.persist(someItem);
            em.persist(anotherItem);

            TernaryUser user = new TernaryUser("John Doe");
            em.persist(user);

            TernaryCategorizedItem linkOne = new TernaryCategorizedItem(user, someItem);
            someCategory.getCategorizedItems().add(linkOne);

            TernaryCategorizedItem linkTwo = new TernaryCategorizedItem(user, anotherItem);
            someCategory.getCategorizedItems().add(linkTwo);

            TernaryCategorizedItem linkThree = new TernaryCategorizedItem(user, someItem);
            anotherCategory.getCategorizedItems().add(linkThree);

//            em.persist(linkOne);
//            em.persist(linkTwo);
//            em.persist(linkThree);
        });
    }


}
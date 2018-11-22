package com.borovyksv.model.associations.manytomany.linkentity;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkCategorizedItemTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPersistAssociation() {
        executeInTransaction(em -> {
            LinkCategory someCategory = new LinkCategory("Some Category");
            LinkCategory anotherCategory = new LinkCategory("Another Category");
            em.persist(someCategory);
            em.persist(anotherCategory);

            LinkItem someItem = new LinkItem("Some Item");
            LinkItem anotherItem = new LinkItem("Another Item");
            em.persist(someItem);
            em.persist(anotherItem);

            LinkCategorizedItem linkOne = new LinkCategorizedItem("John Doe", someCategory, someItem);
            LinkCategorizedItem linkTwo = new LinkCategorizedItem("John Doe", anotherCategory, someItem);
            LinkCategorizedItem linkThree = new LinkCategorizedItem("John Doe", someCategory, anotherItem);

            em.persist(linkOne);
            em.persist(linkTwo);
            em.persist(linkThree);
        });
    }
    
}
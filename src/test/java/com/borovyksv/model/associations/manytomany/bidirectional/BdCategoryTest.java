package com.borovyksv.model.associations.manytomany.bidirectional;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BdCategoryTest extends BaseJpaTest {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPersistAssociation() {
        executeInTransaction(em -> {
            BdCategory someCategory = new BdCategory("Some Category");
            BdCategory anotherCategory = new BdCategory("Another Category");

            BdItem someItem = new BdItem("Some Item");
            BdItem anotherItem = new BdItem("Another Item");

            someCategory.getItems().add(someItem);
            someItem.getCategories().add(someCategory);

            anotherCategory.getItems().add(anotherItem);
            anotherItem.getCategories().add(anotherCategory);

            assertNull(someCategory.getId());
            assertNull(anotherCategory.getId());

            em.persist(someCategory);
            em.persist(anotherCategory);

            assertNotNull(someCategory.getId());
            assertNotNull(anotherCategory.getId());
        });
    }
}
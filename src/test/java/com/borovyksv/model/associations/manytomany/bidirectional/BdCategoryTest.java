package com.borovyksv.model.associations.manytomany.bidirectional;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BdCategoryTest extends CrudJpaTest<BdCategory> {

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

    @Override
    protected BdCategory getTestEntity() {
        BdCategory testCategory = new BdCategory("Test Category");
        BdItem testItem = new BdItem("Test Item");
        testCategory.getItems().add(testItem);
        testItem.getCategories().add(testCategory);
        return testCategory;
    }

    protected String getEntityTableName() {
        return BdCategory.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(BdCategory entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(BdCategory originalEntity) {
        return originalEntity.getName();
    }

    @Override
    protected String updateAndGetEntityValue(BdCategory entityToUpdate) {
        String updatedItemName = "Updated Category";
        entityToUpdate.setName(updatedItemName);
        return updatedItemName;
    }

}
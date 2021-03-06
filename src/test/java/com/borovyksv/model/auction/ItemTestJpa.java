package com.borovyksv.model.auction;

import com.borovyksv.base.CrudJpaTest;
import com.borovyksv.util.TestUtil;
import org.junit.BeforeClass;

public class ItemTestJpa extends CrudJpaTest<Item> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected Item getTestEntity() {
        return TestUtil.getItem();
    }

    @Override
    protected String getEntityTableName() {
        return "Item";
    }

    @Override
    protected Long getEntityId(Item entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(Item originalEntity) {
        return originalEntity.getName();
    }

    @Override
    protected String updateAndGetEntityValue(Item entityToUpdate) {
        String newItemName = "New ItemName";
        entityToUpdate.setName(newItemName);
        return newItemName;
    }
}

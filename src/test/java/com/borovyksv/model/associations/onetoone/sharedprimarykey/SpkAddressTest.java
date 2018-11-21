package com.borovyksv.model.associations.onetoone.sharedprimarykey;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class SpkAddressTest extends CrudJpaTest<SpkAddress> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testSharedPrimaryKey() {
        executeInTransaction(em -> {
            SpkAddress SpkAddress = getTestEntity();
            em.persist(SpkAddress);

            SpkUser SpkUser = new SpkUser(SpkAddress.getId(), "SpkUsername", SpkAddress);
            em.persist(SpkUser);
        });
    }

    @Override
    protected SpkAddress getTestEntity() {
        return new SpkAddress("Street", "Zipcode", "City");
    }

    protected String getEntityTableName() {
        return SpkAddress.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(SpkAddress entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(SpkAddress originalEntity) {
        return originalEntity.getCity();
    }

    @Override
    protected String updateAndGetEntityValue(SpkAddress entityToUpdate) {
        String updatedItemName = "new City";
        entityToUpdate.setCity(updatedItemName);
        return updatedItemName;
    }
}
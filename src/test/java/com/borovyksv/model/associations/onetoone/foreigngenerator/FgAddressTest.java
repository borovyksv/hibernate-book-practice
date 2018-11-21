package com.borovyksv.model.associations.onetoone.foreigngenerator;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;


public class FgAddressTest extends CrudJpaTest<FgAddress> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testSharedPrimaryKey() {
        executeInTransaction(em ->{
            FgAddress address = getTestEntity();
            FgUser user = address.getUser();
            em.persist(user);
        });
    }

    @Override
    protected FgAddress getTestEntity() {
        FgUser user = new FgUser("some Username");
        FgAddress address = new FgAddress("Street", "Zipcode", "City", user);
        user.setAddress(address);
        return address;
    }

    protected String getEntityTableName() {
        return FgAddress.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(FgAddress entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(FgAddress originalEntity) {
        return originalEntity.getCity();
    }

    @Override
    protected String updateAndGetEntityValue(FgAddress entityToUpdate) {
        String updatedItemName = "new City";
        entityToUpdate.setCity(updatedItemName);
        return updatedItemName;
    }

    @Override
    public void testDelete() {
        //ignore
    }
}
package com.borovyksv.model.associations.onetoone.sharedprimarykey;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressTest extends CrudJpaTest<Address> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testSharedPrimaryKey() {
        executeInTransaction(em -> {
            Address address = getTestEntity();
            em.persist(address);

            User user = new User(address.getId(), "Username", address);
            em.persist(user);
        });
    }

    @Override
    protected Address getTestEntity() {
        return new Address("Street", "Zipcode", "City");
    }

    protected String getEntityTableName() {
        return Address.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(Address entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(Address originalEntity) {
        return originalEntity.getCity();
    }

    @Override
    protected String updateAndGetEntityValue(Address entityToUpdate) {
        String updatedItemName = "new City";
        entityToUpdate.setCity(updatedItemName);
        return updatedItemName;
    }
}
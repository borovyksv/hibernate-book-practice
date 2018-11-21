package com.borovyksv.model.associations.onetoone.foreigngenerator;

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
        executeInTransaction(em ->{
            Address address = getTestEntity();
            User user = address.getUser();
            em.persist(user);
        });
    }

    @Override
    protected Address getTestEntity() {
        User user = new User("some Username");
        Address address = new Address("Street", "Zipcode", "City", user);
        user.setAddress(address);
        return address;
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

    @Override
    public void testDelete() {
        //ignore
    }
}
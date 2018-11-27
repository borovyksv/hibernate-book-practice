package com.borovyksv.model.associations.onetoone.foreignkey;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class UserTest extends CrudJpaTest<FkUser> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Override
    protected FkUser getTestEntity() {
        return new FkUser("FkUser Name",
                new FkAddress("Street", "Zipcode", "City"));
    }

    protected String getEntityTableName() {
        return FkUser.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(FkUser entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(FkUser originalEntity) {
        return originalEntity.getUsername();
    }

    @Override
    protected String updateAndGetEntityValue(FkUser entityToUpdate) {
        String updatedUserName = "new City";
        entityToUpdate.setUsername(updatedUserName);
        return updatedUserName;
    }
}
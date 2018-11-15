package com.borovyksv.model.auction;

import com.borovyksv.base.CrudJpaTest;
import com.borovyksv.model.auction.zipcode.GermanZipcode;
import com.borovyksv.model.auction.zipcode.SwissZipcode;
import com.borovyksv.util.TestUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserTestJpa extends CrudJpaTest<User> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }



    @Test //NOTE: Attribute Converter cause redundant dirty updates
    public void testZipcodeConverter() {
        executeInTransaction(entityManager -> {
            User originalUser = TestUtil.getUser();
            entityManager.persist(originalUser);
            User retrievedUser = getEntityById(entityManager, originalUser.getId());
            assertTrue(retrievedUser.getHomeAddress().getZipcode() instanceof GermanZipcode);

            retrievedUser.getHomeAddress().setZipcode(new SwissZipcode("1234"));
            User retrievedUser2 = getEntityById(entityManager, originalUser.getId());
            assertTrue(retrievedUser2.getHomeAddress().getZipcode() instanceof SwissZipcode);
        });
    }


    @Override
    protected User getTestEntity() {
        return TestUtil.getUser();
    }

    @Override
    protected String getEntityTableName() {
        return "User";
    }

    @Override
    protected Long getEntityId(User entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(User originalEntity) {
        return originalEntity.getUserName();
    }

    @Override
    protected String updateAndGetEntityValue(User entityToUpdate) {
        String newUsername = "New Username";
        entityToUpdate.setUserName(newUsername);
        return newUsername;
    }
}

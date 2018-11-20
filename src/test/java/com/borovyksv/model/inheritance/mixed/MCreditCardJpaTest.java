package com.borovyksv.model.inheritance.mixed;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class MCreditCardJpaTest extends CrudJpaTest<MCreditCard> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected MCreditCard getTestEntity() {
        return MCreditCard.builder()
                .owner("owner")
                .cardNumber("7894 9874 7894 1234")
                .expMonth("March")
                .expYear("2020")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "MCreditCard";
    }

    @Override
    protected Long getEntityId(MCreditCard entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(MCreditCard originalEntity) {
        return originalEntity.getExpYear();
    }

    @Override
    protected String updateAndGetEntityValue(MCreditCard entityToUpdate) {
        String expYear = "2099";
        entityToUpdate.setExpYear(expYear);
        return expYear;
    }
}
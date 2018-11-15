package com.borovyksv.model.inheritance.singletable;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class SCreditCardJpaTest extends CrudJpaTest<SCreditCard> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected SCreditCard getTestEntity() {
        return SCreditCard.builder()
                .owner("owner")
                .cardNumber("7894 9874 7894 1234")
                .expMonth("March")
                .expYear("2020")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "SCreditCard";
    }

    @Override
    protected Long getEntityId(SCreditCard entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(SCreditCard originalEntity) {
        return originalEntity.getExpYear();
    }

    @Override
    protected String updateAndGetEntityValue(SCreditCard entityToUpdate) {
        String expYear = "2099";
        entityToUpdate.setExpYear(expYear);
        return expYear;
    }
}
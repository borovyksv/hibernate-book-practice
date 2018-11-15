package com.borovyksv.model.inheritance.tableperclass;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;

public class TCreditCardJpaTest extends BaseJpaTest<TCreditCard> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected TCreditCard getTestEntity() {
        return TCreditCard.builder()
                .owner("owner")
                .cardNumber("7894 9874 7894 1234")
                .expMonth("March")
                .expYear("2020")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "TCreditCard";
    }

    @Override
    protected Long getEntityId(TCreditCard entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(TCreditCard originalEntity) {
        return originalEntity.getExpYear();
    }

    @Override
    protected String updateAndGetEntityValue(TCreditCard entityToUpdate) {
        String expYear = "2099";
        entityToUpdate.setExpYear(expYear);
        return expYear;
    }
}
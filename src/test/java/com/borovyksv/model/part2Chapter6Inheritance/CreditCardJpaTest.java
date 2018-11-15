package com.borovyksv.model.part2Chapter6Inheritance;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;

public class CreditCardJpaTest extends BaseJpaTest<CreditCard> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected CreditCard getTestEntity() {
        return CreditCard.builder()
                .owner("owner")
                .cardNumber("7894 9874 7894 1234")
                .expMonth("March")
                .expYear("2020")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "CreditCard";
    }

    @Override
    protected Long getEntityId(CreditCard entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(CreditCard originalEntity) {
        return originalEntity.getExpYear();
    }

    @Override
    protected String updateAndGetEntityValue(CreditCard entityToUpdate) {
        String expYear = "2099";
        entityToUpdate.setExpYear(expYear);
        return expYear;
    }
}
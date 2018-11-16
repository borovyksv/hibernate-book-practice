package com.borovyksv.model.inheritance.mappedsuperclass;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class MapCreditCardJpaTest extends CrudJpaTest<MapCreditCard> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected MapCreditCard getTestEntity() {
        return MapCreditCard.builder()
                .owner("owner")
                .cardNumber("7894 9874 7894 1234")
                .expMonth("March")
                .expYear("2020")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "MapCreditCard";
    }

    @Override
    protected Long getEntityId(MapCreditCard entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(MapCreditCard originalEntity) {
        return originalEntity.getExpYear();
    }

    @Override
    protected String updateAndGetEntityValue(MapCreditCard entityToUpdate) {
        String expYear = "2099";
        entityToUpdate.setExpYear(expYear);
        return expYear;
    }
}
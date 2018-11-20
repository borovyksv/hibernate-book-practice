package com.borovyksv.model.inheritance.mappedsuperclass;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class MapBankAccountJpaTest extends CrudJpaTest<MapBankAccount> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Override
    protected MapBankAccount getTestEntity() {
        return MapBankAccount.builder()
                .owner("owner")
                .account("Some bank account")
                .bankName("World Wide Bank")
                .swift("AD1200012030200359100100")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "MapBankAccount";
    }

    @Override
    protected Long getEntityId(MapBankAccount entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(MapBankAccount originalEntity) {
        return originalEntity.getBankName();
    }

    @Override
    protected String updateAndGetEntityValue(MapBankAccount entityToUpdate) {
        String newBankName = "My new Bank";
        entityToUpdate.setBankName(newBankName);
        return newBankName;
    }
}

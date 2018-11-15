package com.borovyksv.model.inheritance.singletable;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class SBankAccountJpaTest extends CrudJpaTest<SBankAccount> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Override
    protected SBankAccount getTestEntity() {
        return SBankAccount.builder()
                .owner("owner")
                .account("Some bank account")
                .bankName("World Wide Bank")
                .swift("AD1200012030200359100100")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "SBankAccount";
    }

    @Override
    protected Long getEntityId(SBankAccount entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(SBankAccount originalEntity) {
        return originalEntity.getBankName();
    }

    @Override
    protected String updateAndGetEntityValue(SBankAccount entityToUpdate) {
        String newBankName = "My new Bank";
        entityToUpdate.setBankName(newBankName);
        return newBankName;
    }
}
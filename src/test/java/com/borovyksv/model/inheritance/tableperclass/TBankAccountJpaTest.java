package com.borovyksv.model.inheritance.tableperclass;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

public class TBankAccountJpaTest extends CrudJpaTest<TBankAccount> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Override
    protected TBankAccount getTestEntity() {
        return TBankAccount.builder()
                .owner("owner")
                .account("Some bank account")
                .bankName("World Wide Bank")
                .swift("AD1200012030200359100100")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "TBankAccount";
    }

    @Override
    protected Long getEntityId(TBankAccount entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(TBankAccount originalEntity) {
        return originalEntity.getBankName();
    }

    @Override
    protected String updateAndGetEntityValue(TBankAccount entityToUpdate) {
        String newBankName = "My new Bank";
        entityToUpdate.setBankName(newBankName);
        return newBankName;
    }
}
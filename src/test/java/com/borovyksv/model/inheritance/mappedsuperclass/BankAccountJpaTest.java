package com.borovyksv.model.inheritance.mappedsuperclass;

import com.borovyksv.base.BaseJpaTest;
import org.junit.BeforeClass;

public class BankAccountJpaTest extends BaseJpaTest<BankAccount> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Override
    protected BankAccount getTestEntity() {
        return BankAccount.builder()
                .owner("owner")
                .account("Some bank account")
                .bankName("World Wide Bank")
                .swift("AD1200012030200359100100")
                .build();
    }

    @Override
    protected String getEntityTableName() {
        return "BankAccount";
    }

    @Override
    protected Long getEntityId(BankAccount entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(BankAccount originalEntity) {
        return originalEntity.getBankName();
    }

    @Override
    protected String updateAndGetEntityValue(BankAccount entityToUpdate) {
        String newBankName = "My new Bank";
        entityToUpdate.setBankName(newBankName);
        return newBankName;
    }
}
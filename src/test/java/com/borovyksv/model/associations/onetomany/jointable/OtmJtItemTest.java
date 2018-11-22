package com.borovyksv.model.associations.onetomany.jointable;

import com.borovyksv.base.CrudJpaTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OtmJtItemTest extends CrudJpaTest<OtmJtItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testPersistAssociation() {
        executeInTransaction(em -> {
            OtmJtItem someItem = new OtmJtItem("Some OtmJtItem");
            em.persist(someItem);
            new OtmJtBid("Some bid", 1728.5, someItem);
            new OtmJtBid("Second bid", 199.5, someItem);
            OtmJtUser user = new OtmJtUser("Some user");
            someItem.setBuyer(user);
            List itemBuyerAssociations = em.createNativeQuery("select * from ITEM_BUYER").getResultList();
            Assert.assertFalse(itemBuyerAssociations.isEmpty());
        });
    }


    @Override
    protected OtmJtItem getTestEntity() {
        Set<OtmJtBid> bids = IntStream
                .range(0, 3)
                .mapToObj(i -> new OtmJtBid("OtmJtBid " + i))
                .collect(Collectors.toSet());
        OtmJtItem item = new OtmJtItem();
        item.setItemName("ItemName");
        item.setBids(bids);
        return item;
    }

    protected String getEntityTableName() {
        return OtmJtItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(OtmJtItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(OtmJtItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(OtmJtItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
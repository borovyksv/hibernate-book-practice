package com.borovyksv.model.associations.onetomany.bidirebtional;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OtmItemTest extends CrudJpaTest<OtmItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testPersistAssociation() {
        executeInTransaction(em -> {
            OtmItem someItem = new OtmItem("Some OtmItem");
            em.persist(someItem);

            OtmBid someBid = new OtmBid("Some bid", 1728.5, someItem);
//            em.persist(someBid);   // if no cascadeType on OtmItem

            OtmBid secondBid = new OtmBid("Second bid", 199.5, someItem);
//            em.persist(secondBid); // if no cascadeType on OtmItem
        });
    }

    @Test
    public void testOrphanRemoval() {
        OtmItem someItem = new OtmItem("Some OtmItem");
        executeInTransaction(em -> {
            em.persist(someItem);

            new OtmBid("Some bid", 1728.5, someItem);
            new OtmBid("Second bid", 199.5, someItem);
        });
        executeInTransaction(em -> {
            OtmItem entityById = getEntityById(em, someItem.getId());
            OtmBid next = entityById.getBids().iterator().next();
            entityById.getBids().remove(next);
            next = entityById.getBids().iterator().next();
            entityById.getBids().remove(next);
        });
    }

    @Override
    protected OtmItem getTestEntity() {
        Set<OtmBid> bids = IntStream
                .range(0, 3)
                .mapToObj(i -> new OtmBid("OtmBid " + i))
                .collect(Collectors.toSet());
        OtmItem item = new OtmItem();
        item.setItemName("ItemName");
        item.setBids(bids);
        return item;
    }

    protected String getEntityTableName() {
        return OtmItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(OtmItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(OtmItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(OtmItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
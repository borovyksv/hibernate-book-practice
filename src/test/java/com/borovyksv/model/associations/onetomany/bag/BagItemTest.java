package com.borovyksv.model.associations.onetomany.bag;

import com.borovyksv.base.CrudJpaTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BagItemTest extends CrudJpaTest<BagItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Test
    public void testBag() {
        BagItem someItem = new BagItem("Some BagItem");
        executeInTransaction(em -> {
            em.persist(someItem);
            new BagBid("Some bid", 1728.5, someItem);
            new BagBid("Second bid", 199.5, someItem);
            Assert.assertEquals(2, someItem.getBids().size());
        });

        executeInTransaction(em -> {
            BagItem entityById = getEntityById(em, someItem.getId());
            BagBid newBid = new BagBid("Third bid", 99.5, entityById);

            //!!! No SELECT (seems like List has no SELECT as well)
            // but Set always loads all bids from DB before adding a new one
            entityById.getBids().add(newBid);
        });
    }

    protected List getAllBidsByItemId(EntityManager entityManager, BagItem itemId) {
        return entityManager.createNativeQuery("select * from BagBid b where b.item_id = :itemId")
                .setParameter("itemId", itemId)
                .getResultList();
    }


    @Override
    protected BagItem getTestEntity() {
        Collection<BagBid> bids = IntStream
                .range(0, 3)
                .mapToObj(i -> new BagBid("BagBid " + i))
                .collect(Collectors.toList());
        BagItem item = new BagItem();
        item.setItemName("ItemName");
        item.setBids(bids);
        return item;
    }

    protected String getEntityTableName() {
        return BagItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(BagItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(BagItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(BagItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
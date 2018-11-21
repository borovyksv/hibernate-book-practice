package com.borovyksv.model.associations.onetomany.ondeletecascade;

import com.borovyksv.base.CrudJpaTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OdcItemTest extends CrudJpaTest<OdcItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }
    
    @Test
    public void testCascadeDelete() {
        OdcItem someItem = new OdcItem("Some OdcItem");
        final Long[] id = {null};
        executeInTransaction(em -> {
            em.persist(someItem);
            id[0] = someItem.getId();
            new OdcBid("Some bid", 1728.5, someItem);
            new OdcBid("Second bid", 199.5, someItem);
            Assert.assertEquals(2, someItem.getBids().size());
        });

        executeInTransaction(em -> {
            OdcItem entityById = getEntityById(em, id[0]);
            Set<OdcBid> referenceToBidsBeforeDeletion = entityById.getBids();

            List bids = getAllBidsByItemId(em, entityById);
            Assert.assertEquals(2, bids.size());

            // cascade DELETE for all related bids
            // PROS: no additional Hibernate Delete Queries
            // CONS: be careful with DB/Memory consistency
            em.remove(entityById);
            List bidsAfterDeletion = getAllBidsByItemId(em, entityById);
            Assert.assertTrue(bidsAfterDeletion.isEmpty());
        });
    }

    protected List getAllBidsByItemId(EntityManager entityManager, OdcItem itemId) {
        return entityManager.createNativeQuery("select * from OdcBid b where b.item_id = :itemId")
                .setParameter("itemId", itemId)
                .getResultList();
    }


    @Override
    protected OdcItem getTestEntity() {
        Set<OdcBid> bids = IntStream
                .range(0, 3)
                .mapToObj(i -> new OdcBid("OdcBid " + i))
                .collect(Collectors.toSet());
        OdcItem item = new OdcItem();
        item.setItemName("ItemName");
        item.setBids(bids);
        return item;
    }

    protected String getEntityTableName() {
        return OdcItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(OdcItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(OdcItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(OdcItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
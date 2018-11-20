package com.borovyksv.model.collections.listofstrings;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("all")
public class LosItemTest extends CrudJpaTest<LosItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }


    // this test causes 1 insert and 10 updates in Image table (column images_ORDER update)
    @Test
    public void testInsertFirstInCollection() {
        executeInTransaction(entityManager -> {
            LosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            LosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().add(0, "Another Image");
        });
    }

    // this test causes 1 delete and 5 updates in Image table (column images_ORDER update)
    @Test
    public void testUpdateCollection() {
        executeInTransaction(entityManager -> {
            LosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            LosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().remove(1);
            retrievedEntity.getImages().remove(retrievedEntity.getImages().size() - 1);
            retrievedEntity.getImages().remove(retrievedEntity.getImages().size() - 2);
            retrievedEntity.getImages().add(0, "Another Image 1");
            retrievedEntity.getImages().add(5, "Another Image 2");
        });
    }


    @Override
    protected LosItem getTestEntity() {
        List<String> images = IntStream
                .range(0, 10)
                .mapToObj(i -> "Image " + i)
                .collect(Collectors.toList());
        return LosItem.builder()
                .images(images)
                .itemName("ItemName")
                .build();
    }

    protected String getEntityTableName() {
        return LosItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(LosItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(LosItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(LosItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
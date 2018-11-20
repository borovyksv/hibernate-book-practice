package com.borovyksv.model.collections.mapofstrings;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MosItemTest extends CrudJpaTest<MosItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testInsertFirstInCollection() {
        executeInTransaction(entityManager -> {
            MosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            MosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().put("another1.png", "Another Image");
        });
    }

    @Test
    public void testUpdateCollection() {
        executeInTransaction(entityManager -> {
            MosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            MosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().remove("Image 1");
            retrievedEntity.getImages().put("another1.png", "Another Image 1");
            retrievedEntity.getImages().put("another2.png", "Another Image 2");
        });
    }


    @Override
    protected MosItem getTestEntity() {
        Map<String, String> images = IntStream
                .range(0, 10)
                .mapToObj(i -> "Image " + i)
                .collect(Collectors.toMap(k -> k + ".png", Function.identity()));
        return MosItem.builder()
                .images(images)
                .itemName("ItemName")
                .build();
    }

    protected String getEntityTableName() {
        return MosItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(MosItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(MosItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(MosItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
package com.borovyksv.model.collections.sortedmapofstrings;

import com.borovyksv.base.CrudJpaTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmosItemTest extends CrudJpaTest<SmosItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testInsertFirstInCollection() {
        executeInTransaction(entityManager -> {
            SmosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            SmosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().put("another1.png", "Another Image");
        });
    }

    @Test
    public void testUpdateCollection() {
        executeInTransaction(entityManager -> {
            SmosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            SmosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().remove("Image 1");
            retrievedEntity.getImages().put("another1.png", "Another Image 1");
            retrievedEntity.getImages().put("another2.png", "Another Image 2");
        });
    }

    @Test
    public void testTreeMapReverseOrder() {
        executeInTransaction(entityManager -> {
            SmosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            Assert.assertEquals("Image 0.png", originalEntity.getImages().firstKey());
        });
        executeInTransaction(entityManager -> {
            List<SmosItem> allEntities = getAllEntities(entityManager);
            System.out.println(allEntities);
            Assert.assertNotEquals("Image 0.png", allEntities.get(0).getImages().firstKey());
        });
    }

    @Override
    protected SmosItem getTestEntity() {
        SortedMap<String, String> images = IntStream
                .range(0, 10)
                .mapToObj(i -> "Image " + i)
                .collect(Collectors.toMap(k -> k + ".png",
                        Function.identity(),
                        (u, v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
                        TreeMap::new));
        return SmosItem.builder()
                .images(images)
                .itemName("ItemName")
                .build();
    }

    protected String getEntityTableName() {
        return SmosItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(SmosItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(SmosItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(SmosItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
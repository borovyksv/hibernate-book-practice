package com.borovyksv.model.collections.orderedmapofstrings;

import com.borovyksv.base.CrudJpaTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class OmosItemTest extends CrudJpaTest<OmosItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.MySQL);
    }

    @Test
    public void testInsertFirstInCollection() {
        executeInTransaction(entityManager -> {
            OmosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            OmosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().put("another1.png", "Another Image");
        });
    }

    @Test
    public void testUpdateCollection() {
        executeInTransaction(entityManager -> {
            OmosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            OmosItem retrievedEntity = getEntityById(entityManager, getEntityId(originalEntity));
            retrievedEntity.getImages().remove("Image 1");
            retrievedEntity.getImages().put("another1.png", "Another Image 1");
            retrievedEntity.getImages().put("another2.png", "Another Image 2");
        });
    }

    @Test
    public void testTreeMapReverseOrder() {
        executeInTransaction(entityManager -> {
            OmosItem originalEntity = getTestEntity();
            entityManager.persist(originalEntity);
            Assert.assertEquals("Image 0.png", originalEntity.getImages().keySet().iterator().next());
        });
        executeInTransaction(entityManager -> {
            List<OmosItem> allEntities = getAllEntities(entityManager);
            System.out.println(allEntities);
            Assert.assertNotEquals("Image 0.png", allEntities.get(0).getImages().keySet().iterator().next());
        });
    }

    @Override
    protected OmosItem getTestEntity() {
        Map<String, String> images = IntStream
                .range(0, 10)
                .mapToObj(i -> "Image " + i)
                .collect(Collectors.toMap(k -> k + ".png",
                        Function.identity(),
                        (u, v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
                        LinkedHashMap::new));
        return OmosItem.builder()
                .images(images)
                .itemName("ItemName")
                .build();
    }

    protected String getEntityTableName() {
        return OmosItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(OmosItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(OmosItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(OmosItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
package com.borovyksv.model.collections.bagofstrings;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BosItemTest extends CrudJpaTest<BosItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected BosItem getTestEntity() {
        Set<String> images = IntStream
                .range(0, 3)
                .mapToObj(i -> "Image " + i)
                .collect(Collectors.toSet());
        return BosItem.builder()
                .images(images)
                .itemName("ItemName")
                .build();
    }

    protected String getEntityTableName() {
        return BosItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(BosItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(BosItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(BosItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
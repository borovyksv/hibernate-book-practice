package com.borovyksv.model.collections.setofstrings;

import com.borovyksv.base.CrudJpaTest;
import com.borovyksv.model.collections.setofstring.SosItem;
import org.junit.BeforeClass;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SosItemTest extends CrudJpaTest<SosItem> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }


    @Override
    protected SosItem getTestEntity() {
        Set<String> images = IntStream
                .range(0, 3)
                .mapToObj(i -> "Image " + i)
                .collect(Collectors.toSet());
        return SosItem.builder()
                .images(images)
                .itemName("ItemName")
                .build();
    }

    protected String getEntityTableName() {
        return SosItem.class.getSimpleName();
    }

    @Override
    protected Long getEntityId(SosItem entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(SosItem originalEntity) {
        return originalEntity.getItemName();
    }

    @Override
    protected String updateAndGetEntityValue(SosItem entityToUpdate) {
        String updatedItemName = "Updated ItemName";
        entityToUpdate.setItemName(updatedItemName);
        return updatedItemName;
    }
}
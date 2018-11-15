package com.borovyksv.model.helloworld;

import com.borovyksv.base.CrudJpaTest;
import org.junit.BeforeClass;


public class JPAHelloWorldTest extends CrudJpaTest<Message> {

    @BeforeClass
    public static void init() {
        emf = getEntityManagerFactory(JpaConfig.H2);
    }

    @Override
    protected Message getTestEntity() {
        Message message = new Message();
        message.setText("Hello world!");
        message.setPayload(new Payload("Hello-title", "Hello-data"));
        return message;
    }

    @Override
    protected String getEntityTableName() {
        return "message";
    }

    @Override
    protected Long getEntityId(Message entity) {
        return entity.getId();
    }

    @Override
    protected String getOriginalEntityValue(Message originalEntity) {
        return originalEntity.getText();
    }

    @Override
    protected String updateAndGetEntityValue(Message entityToUpdate) {
        String text = "Hello world!";
        entityToUpdate.setText(text);
        return text;
    }
}
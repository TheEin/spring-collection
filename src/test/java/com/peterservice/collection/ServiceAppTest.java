package com.peterservice.collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Example test using custom properties
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApp.class, properties = {
        "spring.cloud.zookeeper.enabled=false",
        "parent1.intProp=555",
        "parent1.longProp=777" })
@ActiveProfiles("service")
public class ServiceAppTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void shouldAnswerWithTrue() {
        ParentComponent parent = ctx.getBean(ParentComponent.class);

        assertEquals(555, parent.getIntProp());

        assertEquals(777, parent.getLongProp());
    }
}

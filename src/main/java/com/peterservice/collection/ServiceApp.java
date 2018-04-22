package com.peterservice.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * Example application
 */
@Slf4j
public class ServiceApp {

    public static void main(String[] args) {
        String ctxLocation = "serviceContext.xml";

        if (args.length > 0) {
            ctxLocation = args[0];
        }

        ConfigurableApplicationContext parentCtx = new ClassPathXmlApplicationContext("applicationContext.xml");

        PropertySourceBootstrapConfiguration bootstrapConfiguration = parentCtx.getBean(PropertySourceBootstrapConfiguration.class);

        bootstrapConfiguration.initialize(parentCtx);

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(parentCtx);

        ctx.setConfigLocation(ctxLocation);

        ctx.refresh();

        MessageSourceAccessor messages = ctx.getBean(MessageSourceAccessor.class);

        final Thread main = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread("shutdown-hook") {

            @Override
            public void run() {
                main.interrupt();
                try {
                    main.join();
                } catch (InterruptedException ignore) {
                }
            }
        });

        log.info(messages.getMessage("app.started", "Application started"));

        ParentComponent parent = ctx.getBean(ParentComponent.class);

        parent.hello();

        try {
            while (true)
                Thread.sleep(1_000_000);
        } catch (InterruptedException ignore) {
        } finally {
            log.info(messages.getMessage("app.exiting", "Application exiting"));
        }
    }
}

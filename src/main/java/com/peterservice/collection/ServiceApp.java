package com.peterservice.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * Example application.<br/>
 * Run with args {@code --spring.config.location=C:\temp\}<br/>
 * to see that additional {@code C:\temp\bootstrap.properties} will overwrite some properties.<br/>
 * It emulates staging configuration.
 */
@Slf4j
@SpringBootApplication
@ImportResource({"applicationContext.xml", "${spring.application.name}.xml"})
public class ServiceApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ServiceApp.class)
                .profiles("service")
                .run(args);

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

        log.info("Parent component: {}", parent);

        try {
            while (true)
                Thread.sleep(1_000_000);
        } catch (InterruptedException ignore) {
        } finally {
            log.info(messages.getMessage("app.exiting", "Application exiting"));
        }
    }
}

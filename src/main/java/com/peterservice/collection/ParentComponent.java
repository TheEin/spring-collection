package com.peterservice.collection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

@Slf4j
@ToString(exclude = "messages", callSuper = true)
public class ParentComponent extends CountableComponent {

    @Getter
    @Setter
    private String stringProp;

    @Getter
    @Setter
    private int intProp;

    @Getter
    @Setter
    private int longProp;

    @Getter
    private ChildComponent firstChild;

    @Getter
    private ChildComponent secondChild;

    @Autowired
    private MessageSourceAccessor messages;

    public void hello() {
        log.info(messages.getMessage("hello", "Hello World!"));
    }

    public void setFirstChild(ChildComponent firstChild) {
        log.debug("Parent component updating: {}", this);

        this.firstChild = firstChild;

        log.debug("Parent component updated: {}", this);
    }

    public void setSecondChild(ChildComponent secondChild) {
        log.debug("Parent component updating: {}", this);

        this.secondChild = secondChild;

        log.debug("Parent component updated: {}", this);
    }
}

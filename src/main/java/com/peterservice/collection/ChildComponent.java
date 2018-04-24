package com.peterservice.collection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ToString(exclude = "peer", callSuper = true)
public class ChildComponent extends CountableComponent {

    @Getter
    @Setter
    private String stringProp;

    @Getter
    @Setter
    private int intProp;

    @Getter
    private ChildComponent peer;

    public void setPeer(ChildComponent peer) {
        log.debug("Child component updating: {}, peer={}", this, this.peer);

        this.peer = peer;

        log.debug("Child component updated: {}, peer={}", this, this.peer);
    }
}

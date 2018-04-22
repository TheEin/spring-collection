package com.peterservice.collection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ToString(exclude = "peer")
public class ChildComponent {

    private static final AtomicLong INSTANCE_SEQ = new AtomicLong();

    @Getter
    @Setter
    private String stringProp;

    @Getter
    @Setter
    private int intProp;

    @Getter
    private ChildComponent peer;

    private final long instanceId = INSTANCE_SEQ.getAndIncrement();

    public void setPeer(ChildComponent peer) {
        log.debug("Child component updating: {}, peer={}", this, this.peer);

        this.peer = peer;

        log.debug("Child component updated: {}, peer={}", this, this.peer);
    }
}

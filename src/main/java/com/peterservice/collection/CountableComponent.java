package com.peterservice.collection;

import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@ToString
public class CountableComponent {

    private static final AtomicLong INSTANCE_SEQ = new AtomicLong();

    private final long instanceId = INSTANCE_SEQ.getAndIncrement();
}

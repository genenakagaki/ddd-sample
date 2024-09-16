package com.genenakagaki.checklist.domain;

import java.util.List;

public class AggregateRoot<T> extends Entity<T> {

    private List<DomainEvent> events;

    public AggregateRoot(T identity) {
        super(identity);
    }
}

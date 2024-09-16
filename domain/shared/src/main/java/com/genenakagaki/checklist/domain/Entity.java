package com.genenakagaki.checklist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Entity<T> {

    private final T id;
}

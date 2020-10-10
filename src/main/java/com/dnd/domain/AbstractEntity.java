package com.dnd.domain;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class AbstractEntity {
    private UUID id;
    private String name;

    public AbstractEntity() {
        this.id = UUID.randomUUID();
    }
}

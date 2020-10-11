package com.dnd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Effect extends AbstractEntity {
    private int duration;
    private String description;
    private int hpPerTurn;
}

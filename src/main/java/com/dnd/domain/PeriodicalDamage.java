package com.dnd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class PeriodicalDamage extends AbstractEntity {
    private int duration;
    private int damagePerTurn;
}


package com.dnd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Character extends AbstractEntity {
    private int hp;
    private List<PeriodicalDamage> periodicalDamages;
}
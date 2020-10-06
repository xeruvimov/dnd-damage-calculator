package com.dnd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Character extends AbstractEntity {
    private int hp;
    private List<PeriodicalDamage> periodicalDamages;
}
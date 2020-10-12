package com.dnd.service;

import com.dnd.domain.DataModel;

import javax.xml.crypto.Data;
import java.util.UUID;

public interface CharacterService {
    void addNewChar(String name, int hp, DataModel dataModel);

    void deleteCharById(UUID id, DataModel dataModel);

    void dealDamage(UUID id, int damage, DataModel dataModel);

    void addNewEffect(UUID id, String name, int duration, String description, int hpPerTurn, DataModel dataModel);
}

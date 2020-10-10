package com.dnd.service;

import com.dnd.domain.DataModel;

public interface CharacterService {
    void addNewChar(String name, int hp, DataModel dataModel);
}

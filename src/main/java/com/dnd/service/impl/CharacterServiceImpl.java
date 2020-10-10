package com.dnd.service.impl;

import com.dnd.domain.Character;
import com.dnd.domain.DataModel;
import com.dnd.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Override
    public void addNewChar(String name, int hp, DataModel dataModel) {
        Character character = new Character();
        character.setName(name);
        character.setHp(hp);
        dataModel.getCharacters().add(character);
    }
}

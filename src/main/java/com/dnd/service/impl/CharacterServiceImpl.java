package com.dnd.service.impl;

import com.dnd.domain.Character;
import com.dnd.domain.DataModel;
import com.dnd.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Override
    public void addNewChar(String name, int hp, DataModel dataModel) {
        Character character = new Character();
        character.setName(name);
        character.setHp(hp);
        dataModel.getCharacters().add(character);
    }

    @Override
    public void deleteCharById(UUID id, DataModel dataModel) {
        dataModel.getCharacters().removeIf(character -> character.getId().equals(id));
    }

    @Override
    public void dealDamage(UUID id, int damage, DataModel dataModel) {
        dataModel.getCharacters().forEach(character -> {
            if (character.getId().equals(id)) {
                character.setHp(character.getHp() - damage);
            }
        });
    }
}

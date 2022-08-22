package com.alkemy.management.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.management.disney.dto.SimpleCharacterDTO;
import com.alkemy.management.disney.entity.CharacterEntity;

@Component
public class SimpleCharacterMapper {

  // Convierte una entidad personaje a un DTO simple de personaje
  public SimpleCharacterDTO characterEntity2SimpleCharacterDTO(CharacterEntity entity) {
    SimpleCharacterDTO characterDTO = new SimpleCharacterDTO();

    characterDTO.setImage(entity.getImage());
    characterDTO.setName(entity.getName());

    return characterDTO;
  }

  // Convierte una liste de entidades de personajes a una lista DTOs simples
  public List<SimpleCharacterDTO> listcharacterEntity2SimpleCharacterDTOS(List<CharacterEntity> entities) {
    List<SimpleCharacterDTO> DTOs = new ArrayList<>();

    for (CharacterEntity character : entities) {
      DTOs.add(characterEntity2SimpleCharacterDTO(character));
    }
    return DTOs;
  }
}

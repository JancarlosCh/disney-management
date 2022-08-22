package com.alkemy.management.disney.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alkemy.management.disney.dto.BasicCharacterDTO;
import com.alkemy.management.disney.entity.CharacterEntity;

@Component
public class BasicCharacterMapper {

  // Convierte un DTO básico de personaje a una entidad personaje
  public CharacterEntity basicCharacterDTO2Entity(BasicCharacterDTO dto) {

    CharacterEntity characterEntity = new CharacterEntity();

    characterEntity.setImage(dto.getImage());
    characterEntity.setName(dto.getName());
    characterEntity.setAge(dto.getAge());
    characterEntity.setWeight(dto.getWeight());
    characterEntity.setHistory(dto.getHistory());

    return characterEntity;
  }

  // Convierte una entidad personaje a un DTO básico de personaje
  public BasicCharacterDTO characterEntity2BasicCharacterDTO(CharacterEntity entity) {

    BasicCharacterDTO basicCharacterDTO = new BasicCharacterDTO();

    basicCharacterDTO.setId(entity.getId());
    basicCharacterDTO.setImage(entity.getImage());
    basicCharacterDTO.setName(entity.getName());
    basicCharacterDTO.setAge(entity.getAge());
    basicCharacterDTO.setWeight(entity.getWeight());
    basicCharacterDTO.setHistory(entity.getHistory());

    return basicCharacterDTO;
  }

  // Convierte un hash set de DTOs básicos de personajes a un hash set de entidades
  public Set<CharacterEntity> hashSetBasicCharacterDTOs2Entities(Set<BasicCharacterDTO> DTOs) {

    Set<CharacterEntity> entities = new HashSet<>();

    for (BasicCharacterDTO basicCharacterDTO : DTOs) {
      entities.add(basicCharacterDTO2Entity(basicCharacterDTO));
    }

    return entities;
  }

  // Convierte un hash set de entidades de personajes a un hash set de DTOs básicos
  public Set<BasicCharacterDTO> hashSetCharacterEntities2BasicCharacterDTOs(Set<CharacterEntity> entities){

    Set<BasicCharacterDTO> DTOs = new HashSet<>();

    for (CharacterEntity characterEntity : entities) {
      DTOs.add(characterEntity2BasicCharacterDTO(characterEntity));
    }
    
    return DTOs;
  }
}
